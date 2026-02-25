@echo off
:: Lightweight Maven wrapper for Windows (calls mvn if available, otherwise uses Docker)
:: If mvn is on PATH, run it directly. Otherwise, if Docker is available, run Maven in a container.

nwhere mvn >nul 2>nul
if %ERRORLEVEL%==0 (
  mvn %*
  exit /b %ERRORLEVEL%
)

nwhere docker >nul 2>nul
if %ERRORLEVEL%==0 (
  docker run --rm -v "%cd%":/workspace -w /workspace maven:3.9.4-eclipse-temurin-21 mvn %*
  exit /b %ERRORLEVEL%
)

echo Error: 'mvn' not found and 'docker' not available. Install Maven or Docker to run builds.
exit /b 1

