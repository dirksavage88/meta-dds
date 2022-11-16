# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/eProsima/Micro-XRCE-DDS-Client;protocol=https \
           file://0001-devtool-branch.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "fda7242ed93a81915a75157a56be11fcbd025a98"

S = "${WORKDIR}/git"

# NOTE: unable to map the following CMake package dependencies: Doxygen GMock microxrcedds_client
DEPENDS += "googletest"

inherit cmake

do_install() {

    install -d ${D}${libdir}
    install -m 0644 ${WORKDIR}/build/libmicroxrcedds_client.a ${D}${libdir}/

    install -d ${D}${base_prefix}/opt/microxrce/
    cp -r ${WORKDIR}/build/examples/* ${D}${base_prefix}/opt/microxrce/
}

#INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN}-dev += "ldflags"

FILES_${PN} = "${libdir}/* ${base_prefix}/opt/*"

OECMAKE_FIND_ROOT_PATH_MODE_PROGRAM = "BOTH"

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE += "-DUCLIENT_BUILD_EXAMPLES=ON -DUCLIENT_SUPERBUILD=ON -DUCLIENT_BUILD_MICROCDR=ON"

