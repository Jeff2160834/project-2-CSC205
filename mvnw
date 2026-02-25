#!/usr/bin/env sh
# Lightweight Maven wrapper script.
# Behavior: if 'mvn' is on PATH, run it; otherwise, if 'docker' is available, run Maven in an official container.
# This provides a reproducible way to run Maven without installing it locally (Docker required in fallback).

if command -v mvn >/dev/null 2>&1; then
  exec mvn "$@"
fi

if command -v docker >/dev/null 2>&1; then
  # Use an official Maven image with Temurin JDK 21. Mount current directory as /workspace.
  docker run --rm -v "$PWD":/workspace -w /workspace maven:3.9.4-eclipse-temurin-21 mvn "$@"
  exit $?
fi

echo "Error: 'mvn' not found and 'docker' not available. Install Maven or Docker to run builds." >&2
exit 1

