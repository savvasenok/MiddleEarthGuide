package xyz.savvamirzoyan.middleearth.ui.diffcallback

import xyz.savvamirzoyan.middleearth.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.middleearth.ui.data.ChapterUi

class ChapterUiDiffCallback : CoreDiffCallback<ChapterUi>(
    { chapterUi1, chapterUi2 ->
        if (chapterUi1 is ChapterUi.Chapter && chapterUi2 is ChapterUi.Chapter) {
            chapterUi1.title == chapterUi2.title
        } else false
    },
    { chapterUi1, chapterUi2 ->
        if (chapterUi1 is ChapterUi.Chapter && chapterUi2 is ChapterUi.Chapter) {
            chapterUi1.id == chapterUi2.id
        } else false
    }
)