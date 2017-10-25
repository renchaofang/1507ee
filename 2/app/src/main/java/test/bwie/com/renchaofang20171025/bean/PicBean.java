package test.bwie.com.renchaofang20171025.bean;

import java.util.List;

/**
 * Created by 1 on 2017/10/25.
 */

public class PicBean {


    /**
     * date : 20171025
     * stories : [{"ga_prefix":"102514","id":9653576,"images":["https://pic3.zhimg.com/v2-7b58d1a1a38b6302d76d2698b0a08d8a.jpg"],"title":"把树抱住后使劲摇摇摇\u2026\u2026你看人家这摘果子的方法清奇的","type":0},{"ga_prefix":"102513","id":9653776,"images":["https://pic1.zhimg.com/v2-714718adf58cc1a111c422d2514e2354.jpg"],"title":"记住这些知识点，去日本迷失在车站里也能会心一笑","type":0},{"ga_prefix":"102512","id":9653346,"images":["https://pic2.zhimg.com/v2-36c115eaf6a006719f71a94cd2993f59.jpg"],"title":"大误 · 让地球从内到外冷下来","type":0},{"ga_prefix":"102511","id":9653785,"images":["https://pic3.zhimg.com/v2-c98d605967a61c53abd811e683e62dde.jpg"],"title":"歌手是怎么创作歌曲的？","type":0},{"ga_prefix":"102510","id":9653414,"images":["https://pic3.zhimg.com/v2-ed8af5cd65b6bca6c9b4966b05e1b72a.jpg"],"title":"虽说「人心狠毒」，为什么很少见到真正有毒的哺乳动物？","type":0},{"ga_prefix":"102509","id":9653540,"images":["https://pic4.zhimg.com/v2-c48d2c752ec7b8b183055667b76596c7.jpg"],"title":"没和身边人一起进大律所，可后来，我却学得比他们多","type":0},{"ga_prefix":"102508","id":9653658,"images":["https://pic3.zhimg.com/v2-8d3803d6014153f1aa9835b47ccd7db2.jpg"],"title":"没有「爆裂脑花」、细思 bug 极多\u2026\u2026唯一的亮点只剩特工代号","type":0},{"ga_prefix":"102507","id":9653620,"images":["https://pic3.zhimg.com/v2-8569d560d951c65cc1c712b8976c8fba.jpg"],"title":"身为杂食性动物的两脚兽，我们要吃蔬菜水果，喵汪星人呢？","type":0},{"ga_prefix":"102507","id":9653718,"images":["https://pic2.zhimg.com/v2-340313b0e29d374f9a7fbe3cb45483e1.jpg"],"title":"姚老板的球队能卖多少钱，起决定作用的是他另一个身份","type":0},{"ga_prefix":"102507","id":9653251,"images":["https://pic3.zhimg.com/v2-d839d13157eb525ed60c34e39fee4d1a.jpg"],"title":"《天才枪手》式的造富神话：非典型批片、中国采购团和弯道超车梦","type":0},{"ga_prefix":"102506","id":9653582,"images":["https://pic1.zhimg.com/v2-ea05acac99ff29b8d1b60de506dbcfe4.jpg"],"title":"瞎扯 · 如何正确地吐槽","type":0}]
     * top_stories : [{"ga_prefix":"102511","id":9653785,"image":"https://pic4.zhimg.com/v2-97d9c4d8c3c673b10772682e5ac0c137.jpg","title":"歌手是怎么创作歌曲的？","type":0},{"ga_prefix":"102507","id":9653718,"image":"https://pic2.zhimg.com/v2-e7582788c34b9d40b7b849ea3458d0dd.jpg","title":"姚老板的球队能卖多少钱，起决定作用的是他另一个身份","type":0},{"ga_prefix":"102507","id":9653251,"image":"https://pic1.zhimg.com/v2-e5b5e2342378517d1ddeb3f26496367c.jpg","title":"《天才枪手》式的造富神话：非典型批片、中国采购团和弯道超车梦","type":0},{"ga_prefix":"102514","id":9653576,"image":"https://pic2.zhimg.com/v2-2f8827e1dd120aecea73713fd27f67d1.jpg","title":"把树抱住后使劲摇摇摇\u2026\u2026你看人家这摘果子的方法清奇的","type":0},{"ga_prefix":"102419","id":9653770,"image":"https://pic3.zhimg.com/v2-3820a42752377cd7cbceff405d79e182.jpg","title":"对我来说，《英雄联盟》全球总决赛就是最好的体育赛事","type":0}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * ga_prefix : 102514
         * id : 9653576
         * images : ["https://pic3.zhimg.com/v2-7b58d1a1a38b6302d76d2698b0a08d8a.jpg"]
         * title : 把树抱住后使劲摇摇摇……你看人家这摘果子的方法清奇的
         * type : 0
         */

        private String ga_prefix;
        private int id;
        private String title;
        private int type;
        private List<String> images;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * ga_prefix : 102511
         * id : 9653785
         * image : https://pic4.zhimg.com/v2-97d9c4d8c3c673b10772682e5ac0c137.jpg
         * title : 歌手是怎么创作歌曲的？
         * type : 0
         */

        private String ga_prefix;
        private int id;
        private String image;
        private String title;
        private int type;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
