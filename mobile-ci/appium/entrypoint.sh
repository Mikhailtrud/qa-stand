#!/bin/sh
set -eu

mkdir -p "$HOME/.android"
if [ -n "${ANDROID_ADB_PRIVATE_KEY:-}" ]; then
  printf '%s\n' "$ANDROID_ADB_PRIVATE_KEY" > "$HOME/.android/adbkey"
  chmod 600 "$HOME/.android/adbkey"
  adb pubkey "$HOME/.android/adbkey" > "$HOME/.android/adbkey.pub"
fi

if [ "${CONNECT_ADB_ON_START:-false}" = "true" ]; then
  adb connect "$ANDROID_SERIAL" >/dev/null
  adb -s "$ANDROID_SERIAL" wait-for-device
fi

exec "$@"
