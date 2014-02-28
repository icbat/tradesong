---
layout: default
title: Devlog
---

{% for post in site.posts %}
{% if post.isDownload %}
{% else %}
<h4><a href="{{site.url}}{{post.url}}">{{post.title}}</a></h4> on {{ post.date | date: "%d %B %Y" }}
{{post.excerpt}} 
{% endif %}
{% endfor %}

