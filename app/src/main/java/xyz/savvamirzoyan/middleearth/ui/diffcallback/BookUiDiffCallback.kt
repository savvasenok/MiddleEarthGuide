package xyz.savvamirzoyan.middleearth.ui.diffcallback

import xyz.savvamirzoyan.middleearth.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.middleearth.ui.data.BookUi

class BookUiDiffCallback : CoreDiffCallback<BookUi>(
    { bookUi1, bookUi2 ->
        if (bookUi1 is BookUi.Book && bookUi2 is BookUi.Book) {
            bookUi1.title == bookUi2.title
        } else false
    },
    { bookUi1, bookUi2 ->
        if (bookUi1 is BookUi.Book && bookUi2 is BookUi.Book) {
            bookUi1.id == bookUi2.id
        } else false
    }
)