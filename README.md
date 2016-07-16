# extract_data_from_webview
From this tutorial you will learn how to extract data from android WebView. I have used Jsoup library to extract data from html page.

jsoup is a Java library for working with real-world HTML. It provides a very convenient API for extracting and manipulating data, using the best of DOM, CSS, and jquery-like methods.

jsoup implements the WHATWG HTML5 specification, and parses HTML to the same DOM as modern browsers do.

1. scrape and parse HTML from a URL, file, or string
2. find and extract data, using DOM traversal or CSS selectors
3.  manipulate the HTML elements, attributes, and text
4. clean user-submitted content against a safe white-list, to prevent XSS attacks
5. output tidy HTML

jsoup is designed to deal with all varieties of HTML found in the wild; from pristine and validating, to invalid tag-soup; jsoup will create a sensible parse tree.
Example

Fetch the Wikipedia homepage, parse it to a DOM, and select the headlines from the In the news section into a list of Elements (online sample):

```java

Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
Elements newsHeadlines = doc.select("#mp-itn b a");

```

## Example

I will extract some texts from www.wikipedia.org and show it in webview. Jsoup library makes it very easy. The required code is pretty simple to achieve this:

```java

  //extract some data from wikipedia website
  //Jsoup API is used extract the data

 private void extractDataFromURL() {
        try {
            Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
            Element content = doc.getElementById("mp-tfa"); //this is one of the divisions id (<div id=mp-tfa>...</div>)
            Elements links = content.getElementsByTag("p"); //this is a para inside divisino <div id=mp-tfa><p>...</p>/div>)
            mHtmlString = links.toString();//now mHtmlString contains the text inside the para
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
