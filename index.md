---
layout: default
title: Devlog
---

{% for post in site.posts %}
<a href="{{site.url}}{{post.url}}">{{post.title}}</a> on {{ post.date | date: "%d %B %Y" }}
{{post.excerpt}} 
{% endfor %}

