SUMMARY = "Add IDS camera driver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit systemd

SRC_URI = " \
    file://uEyeSDK-4.90.00-ARM_LINUX_IDS_GNUEABI_HF.tgz \
    file://ueye-drivers.service \
"

S = "${WORKDIR}"

do_install() {
	install -d ${D}/etc

	install -d ${D}/etc/init.d
    install -m 0755 ${S}/etc/init.d/* ${D}/etc/init.d

	install -d ${D}/etc/network
	install -d ${D}/etc/network/if-post-up.d
    install -m 0755 ${S}/etc/network/if-post-up.d/* ${D}/etc/network/if-post-up.d
	install -d ${D}/etc/network/if-pre-down.d
    install -m 0755 ${S}/etc/network/if-pre-down.d/* ${D}/etc/network/if-pre-down.d

	install -d ${D}/etc/udev
	install -d ${D}/etc/udev/rules.d
    install -m 0644 ${S}/etc/udev/rules.d/* ${D}/etc/udev/rules.d

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/ueye-drivers.service ${D}${systemd_unitdir}/system
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "ueye-drivers.service"

PACKAGE_ARCH = "${MACHINE_ARCH}"

