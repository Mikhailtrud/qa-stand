FROM ghcr.io/cirruslabs/android-sdk:35 AS android-sdk

FROM node:22-bookworm-slim

ENV ANDROID_HOME=/opt/android-sdk
ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV PATH="${ANDROID_HOME}/platform-tools:${PATH}"

ARG ANDROID_BUILD_TOOLS_VERSION=35.0.0

COPY --from=android-sdk /opt/android-sdk-linux/platform-tools/ /opt/android-sdk/platform-tools/
COPY --from=android-sdk /opt/android-sdk-linux/build-tools/35.0.0/ /opt/android-sdk/build-tools/35.0.0/

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl openjdk-17-jre-headless \
    && test -x "${ANDROID_HOME}/platform-tools/adb" \
    && test -x "${ANDROID_HOME}/build-tools/${ANDROID_BUILD_TOOLS_VERSION}/apksigner" \
    && "${ANDROID_HOME}/build-tools/${ANDROID_BUILD_TOOLS_VERSION}/apksigner" --version \
    && "${ANDROID_HOME}/platform-tools/adb" version \
    && npm install --global appium@3.7.0 \
    && appium driver install uiautomator2@8.5.0 \
    && rm -rf /var/lib/apt/lists/*

COPY entrypoint.sh /usr/local/bin/mobile-entrypoint
RUN chmod +x /usr/local/bin/mobile-entrypoint

EXPOSE 4723
ENTRYPOINT ["mobile-entrypoint"]
CMD ["appium", "--address", "0.0.0.0", "--port", "4723", "--base-path", "/"]
