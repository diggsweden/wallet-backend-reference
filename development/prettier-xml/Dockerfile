# SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
#
# SPDX-License-Identifier: EUPL-1.2

FROM node:18-bullseye-slim

ARG HTTP_PROXY
ARG HTTPS_PROXY
ARG NO_PROXY
ARG NODE_OPTIONS
ARG NODE_EXTRA_CA_CERTS

WORKDIR /app

COPY package.json package-lock.json ./
COPY proxy-cert.crt* ./

RUN npm install

WORKDIR /app/source
ENTRYPOINT ["../node_modules/.bin/prettier", "--plugin=@prettier/plugin-xml", "**/*.xml"]
