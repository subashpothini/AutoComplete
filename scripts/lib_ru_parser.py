import urllib2
import re

category_url = "http://lib.ru/TALES/"
re_title = "<title>(.+)<\/title>"
re_txt = "<A HREF\=([0-9a-zA-Z]+\.txt)>"
re_author = "<\/small><\/tt> <A HREF=(.+?)><b>.+?<\/b><\/A>"

def http_get(url):
  data = urllib2.urlopen(url)
  return data.read()

def good_sub_url(url):
  return ("http" not in url) and (url[-1] == "/")

def good_url(url):
  return ("http" in url) and ("lib.ru" in url) and (url[-1] == "/")

def parse_category(url):
  data = http_get(url)
  matches = re.findall(re_author, data)
  books = []

  for match in matches:
    if good_sub_url(match):
      author_url = url + match
      books.append(parse_author(author_url)) 
    elif good_url(match):
      author_url = match
      books.append(parse_author(author_url))


  return books

def parse_author(url):
  data = http_get(url)
  matches = re.findall(re_txt, data)
  books = []

  for match in matches:
    book_url = url + match
    books.append(parse_book(book_url))

  return books

def parse_book(url):
  data = http_get(url)
  matches = re.findall(re_title, data)
  title = matches[0]
  return [title, url]

books = parse_category(category_url)

for book in books:
  title = book[0]
  url = book[1]

  print title
  print url
