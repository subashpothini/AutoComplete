import urllib2
import re
import sys

category_url = "http://lib.ru/TALES/"
re_title = "<title>(.+)<\/title>"
re_txt = "<A HREF\=([0-9a-zA-Z]+\.txt)>"
re_author = "<\/small><\/tt> <A HREF=(.+?)><b>.+?<\/b><\/A>"
re_shtml = "<DL><DT><li><A HREF=(.+?)><b>.+?<\/b><\/A>"

def http_get(url):
  data = urllib2.urlopen(url)
  meta = str(data.info())
  res = str(data.read())

  if "koi8" in meta:
    res = res.decode("koi8-r")
  elif "windows" in meta:
    res = res.decode("cp1251")

  return res

def good_sub_url(url):
  return ("http" not in url) and (url[-1] == "/")

def good_url(url):
  return ("http" in url) and ("lib.ru" in url) and (url[-1] == "/")

def parse_category(url):
  data = http_get(url)
  matches = re.findall(re_author, data)

  for match in matches:
    if good_sub_url(match):
      author_url = url + match
      parse_author(author_url) 
    elif good_url(match):
      author_url = match
      parse_author(author_url)

def parse_author(url):
  data = http_get(url)

  matches = re.findall(re_txt, data)
  for match in matches:
    book_url = url + match
    parse_book_txt(book_url)

  matches = re.findall(re_shtml, data)
  for match in matches:
    book_url = url + match
    parse_book_shtml(book_url)

  sys.stdout.flush()

def parse_book_txt(url):
  data = http_get(url)
  matches = re.findall(re_title, data)
  title = matches[0]
  print title
  print url

def parse_book_shtml(url):
  data = http_get(url)
  matches = re.findall(re_title, data)
  title = matches[0]
  real_title = title[title.index(':') + 2:]
  print real_title
  print url

parse_category(category_url)
