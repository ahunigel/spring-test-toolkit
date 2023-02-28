package com.github.ahunigel.test.junit


import spock.lang.Specification

import static com.github.ahunigel.test.junit.Assertions.*

/**
 * Created by Nigel.Zheng on 4/14/20 014.
 *
 * @author Nigel.Zheng
 */
class AssertionsTest extends Specification {

    def "assert not empty list"() {
        when:
        List list = Arrays.asList('test', null)
        then:
        assertNotEmpty(list)
        assertNotEmpty('list should not be empty', list)
    }


    def "assert blank list"() {
        when:
        List list = Arrays.asList(null, null)
        then:
        assertBlank(list)
        assertBlank('list should be blank', list)
    }


    def "assert not blank list"() {
        when:
        List list = Arrays.asList('test', 'null')
        then:
        assertNotBlank(list)
        assertNotBlank('list should not be blank', list)
    }


    def "assert not empty set"() {
        given:
        Set set = new HashSet()
        set.add('test')
        when:
        set.add(null)
        then:
        assertNotEmpty(set)
        assertNotEmpty('set should not be empty', set)
    }


    def "assert blank set"() {
        given:
        Set set = new HashSet()
        set.add(null)
        when:
        set.add(null)
        then:
        assertBlank(set)
        assertBlank('set should be blank', set)
    }


    def "assert not blank set"() {
        given:
        Set set = new HashSet()
        set.add('test')
        when:
        set.add('null')
        then:
        assertNotBlank(set)
        assertNotBlank('list should not be blank', set)
    }


    def "assert not empty map"() {
        given:
        Map map = new HashMap<>()
        when:
        map['key'] = 'val'
        then:
        assertNotEmpty(map)
        assertNotEmpty('map should not be empty', map)
    }


    def "assert not empty string"() {
        when:
        String s = ' '
        then:
        assertNotEmpty(s)
        assertNotEmpty('string should not be empty', s)
    }


    def "assert not blank string"() {
        when:
        String s = ' s '
        then:
        assertNotBlank(s)
        assertNotBlank('string should not be blank', s)
    }
}
