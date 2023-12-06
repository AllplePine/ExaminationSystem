package utils;

public class Config {
    public static String CONFIG_FILE = "WEB-INF\\config.xml";
    private int interval = 30;
    private int pagesize = 30;
    private int timegap = 15;
    private int minfilesize = 512;
    private int maxfilesize = 131072;
    private boolean candelete = true;
    private String interval_desc = "指定扫描考试信息的间隔时间，单位为 分钟。";
    private String pagesize_desc = "指定分页查询时每页显示记录数的默认值（查询页面中可以更改）。";
    private String timegap_desc = "指定手工开启考试时允许的最大提前量，单位为分钟";
    private String minfilesize_desc = "指定上传文件的长度下限（字节），低于此值发出警告";
    private String maxfilesize_desc = "指定上传文件的长度上限（字节），高于此值发出警告";
    private String candelete_desc = "设置是否允许教师自己清理和删除考试信息。";

    public Config() {
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getPagesize() {
        return this.pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTimegap() {
        return this.timegap;
    }

    public void setTimegap(int timegap) {
        this.timegap = timegap;
    }

    public int getMinfilesize() {
        return this.minfilesize;
    }

    public void setMinfilesize(int minfilesize) {
        this.minfilesize = minfilesize;
    }

    public int getMaxfilesize() {
        return this.maxfilesize;
    }

    public void setMaxfilesize(int maxfilesize) {
        this.maxfilesize = maxfilesize;
    }

    public boolean isCandelete() {
        return this.candelete;
    }

    public void setCandelete(boolean candelete) {
        this.candelete = candelete;
    }

    public String getInterval_desc() {
        return this.interval_desc;
    }

    public void setInterval_desc(String interval_desc) {
        this.interval_desc = interval_desc;
    }

    public String getPagesize_desc() {
        return this.pagesize_desc;
    }

    public void setPagesize_desc(String pagesize_desc) {
        this.pagesize_desc = pagesize_desc;
    }

    public String getTimegap_desc() {
        return this.timegap_desc;
    }

    public void setTimegap_desc(String timegap_desc) {
        this.timegap_desc = timegap_desc;
    }

    public String getMinfilesize_desc() {
        return this.minfilesize_desc;
    }

    public void setMinfilesize_desc(String minfilesize_desc) {
        this.minfilesize_desc = minfilesize_desc;
    }

    public String getMaxfilesize_desc() {
        return this.maxfilesize_desc;
    }

    public void setMaxfilesize_desc(String maxfilesize_desc) {
        this.maxfilesize_desc = maxfilesize_desc;
    }

    public String getCandelete_desc() {
        return this.candelete_desc;
    }

    public void setCandelete_desc(String candelete_desc) {
        this.candelete_desc = candelete_desc;
    }
}
