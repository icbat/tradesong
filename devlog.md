---
layout: default
title: Devlog
---

{% for post in site.posts %}
{% if post.isDownload %}
{% else %}
<div class="devlogPostTitle">
	<a href="{{site.url}}{{post.url}}">{{post.title}}</a> on {{ post.date | date: "%d %B %Y" }}
</div>
{{post.excerpt}} 
{% endif %}
{% endfor %}

