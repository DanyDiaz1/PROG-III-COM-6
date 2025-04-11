package com.dany.BeansExample;




public class Version {

    private final int major;
    private final int minor;
    private final int revision;

    public Version(int major, int minor, int revision) {
        this.major = major;
        this.minor = minor;
        this.revision = revision;
    }

    @Override
    public String toString() {
        return "Version{" +
                "major=" + major +
                ", minor=" + minor +
                ", revision=" + revision +
                '}';
    }
}
