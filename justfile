# SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government
#
# SPDX-License-Identifier: CC0-1.0

# Quality checks and automation for Wallet Backend Reference.
#
# Lint/security orchestration is delegated to nanolinter (pinned in
# .mise.toml, plan in nanolinter.toml). `just lint` runs the verify plan;
# the per-check recipes are thin `*args`-forwarding wrappers around
# `nanolinter lint <check>`.
#
# Quick start:
#   mise install   # install nanolinter + every check tool
#   just doctor    # confirm tool health
#   just verify    # run the verify plan + tests

maven_opts := "--batch-mode --no-transfer-progress --errors -Dstyle.color=always"

CYAN_BOLD := "\\033[1;36m"
GREEN := "\\033[1;32m"
BLUE := "\\033[1;34m"
RED := "\\033[1;31m"
NC := "\\033[0m"

# ==================================================================================== #
# DEFAULT
# ==================================================================================== #

# Display available recipes
default:
    @printf "{{CYAN_BOLD}} Wallet Backend Reference{{NC}}\n\n"
    @printf "Quick start: {{GREEN}}mise install{{NC}} | {{BLUE}}just verify{{NC}}\n\n"
    @just --list --unsorted

# ==================================================================================== #
# SETUP
# ==================================================================================== #

# ▪ Install pinned dev tools (nanolinter + check tools)
[group('setup')]
install: tools-install

# Install every tool pinned in .mise.toml
[group('setup')]
tools-install:
    @mise install

# Upgrade pinned tools and reinstall
[group('setup')]
tools-update:
    @mise upgrade
    @mise install

# Show nanolinter's tool/runtime health for the current verify plan
[group('setup')]
doctor: (_require-tool "nanolinter" "Install: mise install  (pinned in .mise.toml)")
    @nanolinter doctor

# ==================================================================================== #
# VERIFY
# ==================================================================================== #

# ▪ Run the nanolinter verify plan + tests
[group('verify')]
verify: lint test

# ==================================================================================== #
# LINT - nanolinter verify plan (plan in nanolinter.toml)
# ==================================================================================== #

# ▪ Run the full verify plan
[group('lint')]
lint *args: (_require-tool "nanolinter" "Install: mise install")
    @nanolinter verify {{args}}

# ▪ Apply safe autofixes for every fix-capable check in the plan
[group('lint-fix')]
lint-fix *args: (_require-tool "nanolinter" "Install: mise install")
    @nanolinter fix {{args}}

# Per-check wrappers (forward flags like --fix / --offline to nanolinter).
[group('lint')]
lint-commits *args:
    @nanolinter lint commits {{args}}

[group('lint')]
lint-secrets *args:
    @nanolinter lint secrets {{args}}

[group('lint')]
lint-license *args:
    @nanolinter lint license {{args}}

[group('lint')]
lint-yaml *args:
    @nanolinter lint yaml {{args}}

[group('lint')]
lint-markdown *args:
    @nanolinter lint markdown {{args}}

[group('lint')]
lint-shell *args:
    @nanolinter lint shell {{args}}

[group('lint')]
lint-actions *args:
    @nanolinter lint actions {{args}}

[group('lint')]
lint-container *args:
    @nanolinter lint container {{args}}

[group('lint')]
lint-xml *args:
    @nanolinter lint xml {{args}}

[group('lint')]
lint-sast *args:
    @nanolinter lint sast {{args}}

[group('lint')]
lint-osv *args:
    @nanolinter lint osv {{args}}

[group('lint')]
lint-java *args:
    @nanolinter lint java {{args}}

# ==================================================================================== #
# TEST / BUILD
# ==================================================================================== #

# ▪ Run tests
[group('test')]
test:
    @mvn {{maven_opts}} test

# ▪ Build project (no tests)
[group('build')]
build:
    @mvn {{maven_opts}} install -DskipTests

# Clean build artifacts
[group('build')]
clean:
    @mvn {{maven_opts}} clean

# ==================================================================================== #
# INTERNAL
# ==================================================================================== #

[private]
_require-tool tool hint="":
    #!/usr/bin/env bash
    if command -v "{{tool}}" >/dev/null 2>&1; then
        exit 0
    fi
    printf "{{RED}}✗ %s not found{{NC}}\n" "{{tool}}" >&2
    if [[ -n "{{hint}}" ]]; then
        printf "  %s\n" "{{hint}}" >&2
    fi
    exit 1
