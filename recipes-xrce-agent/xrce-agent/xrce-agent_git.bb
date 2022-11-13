# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/dirksavage88/Micro-XRCE-DDS-Agent.git;protocol=https \
           file://0001-buildable-xrce-dds-agent.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "f984380d092f593cde35592be2f7571fb2bfa365"

S = "${WORKDIR}/git"

# NOTE: unable to map the following CMake package dependencies: GMock
DEPENDS = "fastcdr fastrtps googletest"

inherit cmake

OECMAKE_FIND_ROOT_PATH_MODE_PROGRAM = "BOTH"

do_install_append() {
    rm -rf ${D}/usr/share
}
# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE += "-DUAGENT_SUPERBUILD=ON -DUAGENT_P2P_PROFILE=OFF -DUAGENT_LOGGER_PROFILE=off"

