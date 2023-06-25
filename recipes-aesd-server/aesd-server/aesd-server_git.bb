LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "gitsm://github.com/cu-ecen-aeld/assignments-3-and-later-apichlkostner.git;protocol=https;branch=master"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "3a71da2b689513dd5c4e54e8412535be041c99e5"

S = "${WORKDIR}/git/server"

TARGET_LDFLAGS += "-pthread -lrt"

inherit update-rc.d

INITSCRIPT_PACKAGES ="${PN}"
INITSCRIPT_NAME:${PN}="aesdsocket-start-stop"

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/aesdsocket ${D}${bindir}/	
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/
}

