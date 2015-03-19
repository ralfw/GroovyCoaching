/**
 * Created by ralfw on 05.03.15.
 *
 * Gelesen bei: http://groovy.codehaus.org/Yaml+and+Groovy
 */

import org.ho.yaml.Yaml

def input = '''
- 1
- apple
- orange
'''
assert Yaml.load(input) == [1, 'apple', 'orange']


def list = ['a, b', 123456]
println Yaml.dump(list)