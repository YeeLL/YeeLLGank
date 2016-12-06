package yeell.yeellgank.model;

import java.io.Serializable;
import java.util.List;

/**
 * 分类数据model
 * Created by YeeLL on 12/6/16.
 */

public class TypeModel implements Serializable{


    public String _id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public List<String> images;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;

}
