SUMMARY = "Builds an external kernel driver from a course"
DESCRIPTION = "${SUMMARY}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "gitsm://github.com/cu-ecen-aeld/assignments-3-and-later-apichlkostner.git;protocol=https;branch=poky"
SRCREV = "0f3ddb453e0dc5dc6d01881c0678ac4d37d06e0a"

S = "${WORKDIR}/git/aesd-char-driver"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-aesd-driver"

inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "S98modules"

FILES:${PN} += "\
    ${bindir}/aesdchar_load \
    ${bindir}/aesdchar_unload \
    ${sysconfdir}/init.d/S98modules \
"

do_install  () {
    install -m 0755 -d ${D}/${bindir}
    install -m 0755 -d ${D}${base_libdir}/modules/${KERNEL_VERSION}
    install -m 0755 -d ${D}/${sysconfdir}/init.d

    install -m 0755 ${S}/aesdchar_load ${D}${bindir}
    install -m 0755 ${S}/aesdchar_unload ${D}${bindir}
    install -m 0755 ${S}/aesdchar.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}
    install -m 0755 ${S}/S98modules ${D}${sysconfdir}/init.d
}