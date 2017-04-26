import json
import requests
import psycopg2

def read_json(filename):
    with open(filename) as file:
        data=json.load(file)
        return data.get('response').get('docs')

def get_nyt_articles(year, month):
    HOST = 'https://api.nytimes.com'
    API_KEY = '4f6bb16e32a24315b83b399f571ecfc0'
    url = HOST + '/svc/archive/v1/{}/{}.json?api-key={}'.format(year,month,API_KEY)
    print url
    # req = urllib2.Request(url)
    # response = urllib2.urlopen(req)
    response = requests.get(url)
    return response.json()
def conn_db():
    # try:
    conn = psycopg2.connect("dbname='article' user='newsuser' host='newsarticle.cmlewmm0iukw.us-east-1.rds.amazonaws.com' password='zhouyuting1231' port='5432'")
    print "Success!"
    # except:
    #     print "I am unable to connect to the database"

    return
if __name__ == "__main__":
    data = read_json('nyt0701.json')
    print "Hello!"
    year = '2017'
    month = '1'
    # print get_nyt_articles(year, month)

    conn_db()

