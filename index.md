---
layout: default
title: Devlog
---

{% for post in site.posts %}
{% if post.isDownload == false %}
{% else %}
<a href="{{site.url}}{{post.url}}">{{post.title}}</a> on {{ post.date | date: "%d %B %Y" }}
{% endif %}
{% endfor %}

