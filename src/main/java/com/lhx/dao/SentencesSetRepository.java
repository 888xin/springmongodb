package com.lhx.dao;

import com.lhx.domain.SentencesSet;

import java.util.List;

/**
 * Created by lhx on 15-1-23 下午2:27
 *
 * @project springmongodb
 * @package com.lhx.dao
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public interface SentencesSetRepository {
    public void insert(SentencesSet sentencesSet);
    public SentencesSet findOne(String id);
    public List<SentencesSet> findAll();
    public List<SentencesSet> findByRegex(String regex) ;
    public void removeOne(String id) ;
    public void removeAll();
    public void findAndModify(String id);
}
