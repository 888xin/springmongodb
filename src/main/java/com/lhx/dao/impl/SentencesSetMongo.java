package com.lhx.dao.impl;

import com.lhx.dao.SentencesSetRepository;
import com.lhx.domain.SentencesSet;

import java.util.List;

/**
 * Created by lhx on 15-1-23 下午2:29
 *
 * @project springmongodb
 * @package com.lhx.dao.impl
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class SentencesSetMongo implements SentencesSetRepository {

    @Override
    public void insert(SentencesSet sentencesSet) {

    }

    @Override
    public SentencesSet findOne(String id) {
        return null;
    }

    @Override
    public List<SentencesSet> findAll() {
        return null;
    }

    @Override
    public List<SentencesSet> findByRegex(String regex) {
        return null;
    }

    @Override
    public void removeOne(String id) {

    }

    @Override
    public void removeAll() {

    }

    @Override
    public void findAndModify(String id) {

    }
}
