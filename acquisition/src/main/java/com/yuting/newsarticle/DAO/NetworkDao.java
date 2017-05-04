package com.yuting.newsarticle.DAO;
import com.yuting.newsarticle.models.Network;
import java.util.List;

/**
 * Created by Ting on 5/3/17.
 */
public interface NetworkDao {
    public List<Object> query(String hql);

    public Network generateNetwork(String hql);

}
