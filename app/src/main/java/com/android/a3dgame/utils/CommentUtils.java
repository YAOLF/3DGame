package com.android.a3dgame.utils;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class CommentUtils {

    /**
     * code : “1”
     * description : {"data":[{"id":"4415672","aid":"3315273","typeid":"0","username":"用户名","ip":"27.41.225.233","ip1":"274118623214025","ip2":"59093204db05a12","ischeck":"1","dtime":"1402624904","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"评论消息","cid":"","reid":"0","topid":"0","floor":"3","reply":"0"},"\u2026\u2026    多个记录"],"paging":{"n":"1","size":8,"start":0,"total":"3","totalpage":1}}
     */

    private String code;
    /**
     * data : [{"id":"4415672","aid":"3315273","typeid":"0","username":"用户名","ip":"27.41.225.233","ip1":"274118623214025","ip2":"59093204db05a12","ischeck":"1","dtime":"1402624904","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"评论消息","cid":"","reid":"0","topid":"0","floor":"3","reply":"0"},"\u2026\u2026    多个记录"]
     * paging : {"n":"1","size":8,"start":0,"total":"3","totalpage":1}
     */

    private DescriptionBean description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DescriptionBean getDescription() {
        return description;
    }

    public void setDescription(DescriptionBean description) {
        this.description = description;
    }

    public static class DescriptionBean {
        /**
         * n : 1
         * size : 8
         * start : 0
         * total : 3
         * totalpage : 1
         */

        private PagingBean paging;
        /**
         * id : 4415672
         * aid : 3315273
         * typeid : 0
         * username : 用户名
         * ip : 27.41.225.233
         * ip1 : 274118623214025
         * ip2 : 59093204db05a12
         * ischeck : 1
         * dtime : 1402624904
         * mid : 0
         * bad : 0
         * good : 0
         * ftype :
         * face : 0
         * msg : 评论消息
         * cid :
         * reid : 0
         * topid : 0
         * floor : 3
         * reply : 0
         */

        private List<DataBean> data;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class PagingBean {
            private String n;
            private int size;
            private int start;
            private String total;
            private int totalpage;

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public int getTotalpage() {
                return totalpage;
            }

            public void setTotalpage(int totalpage) {
                this.totalpage = totalpage;
            }
        }

        public static class DataBean {
            private String id;
            private String aid;
            private String typeid;
            private String username;
            private String ip;
            private String ip1;
            private String ip2;
            private String ischeck;
            private String dtime;
            private String mid;
            private String bad;
            private String good;
            private String ftype;
            private String face;
            private String msg;
            private String cid;
            private String reid;
            private String topid;
            private String floor;
            private String reply;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getIp1() {
                return ip1;
            }

            public void setIp1(String ip1) {
                this.ip1 = ip1;
            }

            public String getIp2() {
                return ip2;
            }

            public void setIp2(String ip2) {
                this.ip2 = ip2;
            }

            public String getIscheck() {
                return ischeck;
            }

            public void setIscheck(String ischeck) {
                this.ischeck = ischeck;
            }

            public String getDtime() {
                return dtime;
            }

            public void setDtime(String dtime) {
                this.dtime = dtime;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getBad() {
                return bad;
            }

            public void setBad(String bad) {
                this.bad = bad;
            }

            public String getGood() {
                return good;
            }

            public void setGood(String good) {
                this.good = good;
            }

            public String getFtype() {
                return ftype;
            }

            public void setFtype(String ftype) {
                this.ftype = ftype;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getReid() {
                return reid;
            }

            public void setReid(String reid) {
                this.reid = reid;
            }

            public String getTopid() {
                return topid;
            }

            public void setTopid(String topid) {
                this.topid = topid;
            }

            public String getFloor() {
                return floor;
            }

            public void setFloor(String floor) {
                this.floor = floor;
            }

            public String getReply() {
                return reply;
            }

            public void setReply(String reply) {
                this.reply = reply;
            }
        }
    }
}
