package com.example.moviesapprappi.db.converters

import com.example.moviesapprappi.model.Keyword

object KeywordListMapper {
  fun mapToStringList(keywords: List<Keyword>): List<String> {
    var list: MutableList<String> = ArrayList()
    for (keyword in keywords) {
      list.add(keyword.name)
    }
    if (list.size > 7) {
      list = list.subList(0, 6)
    }
    return list
  }
}
