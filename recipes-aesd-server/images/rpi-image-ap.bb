include recipes-core/images/core-image-base.bb

COMPATIBLE_MACHINE = "^rpi$"

IMAGE_INSTALL:append = " packagegroup-rpi-test"

CORE_IMAGE_EXTRA_INSTALL += "aesd-server"
CORE_IMAGE_EXTRA_INSTALL += "aesd-driver"
CORE_IMAGE_EXTRA_INSTALL += "openssh"

