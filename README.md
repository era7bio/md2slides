
# md2slides #

A wrapper around pandoc for producing HTML5 slides from markdown files; it relies on one of our [pandoc templates](https://github.com/era7bioinformatics/.pandoc) for doing it.

## install ##

This is a [conscript](http://github.com/n8han/conscript) app, so

``` bash
# is that easy:
cs era7bioinformatics/md2slides
```

**important** `md2slides` assumes that you have pandoc templates `slides.html`, `slidesWhite.html` under your pandoc datadir. _If_ you use our [pandoc datadir](https://github.com/era7bioinformatics/.pandoc), you're all set :)

## use ##

just type `md2slides` and you'll see a usage message.