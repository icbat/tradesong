---
layout: default
title: Devlog
---

{% for post in site.posts %}
{% if post.isDownload %}
{% else %}
<h4><a href="{{site.url}}{{post.url}}">{{post.title}}</a> on {{ post.date | date: "%d %B %Y" }}</h4>
{{post.excerpt}} 
{% endif %}
{% endfor %}

