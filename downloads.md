---
layout: default
title: Downloads
---

{% for post in site.posts %}
{% if post.isDownload %}
<a href="{{site.url}}{{post.url}}">{{post.title}}</a> on {{ post.date | date: "%d %B %Y" }}
{{post.excerpt}} 
{% endif %}
{% endfor %}

