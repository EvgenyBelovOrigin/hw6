package ru.netology

interface Attachment {
    val type: String
}

data class PhotoAttachment(val photo: Photo, override val type: String = "photo") : Attachment

data class Photo(
    val id: Int,
    val text: String,
    val sizes: Array<PhotoSizesArray>?,
    val albumId: Int = 1,
    val ownerId: Int = 1,
    val userId: Int = 1,
    val date: Int = 212121254,
    val width: Int = 300,
    val height: Int = 200

) {
    class PhotoSizesArray(val type: String, val url: String, val width: Int, val height: Int)
}

data class NoteAttachment(val note: Note, override val type: String = "note") : Attachment

data class Note(
    val id: Int,
    val date: Int,
    val ownerId: Int = 1,
    val title: String? = null,
    val text: String? = null,
    val comments: Int = 0,
    val readComments: Int = 0,
    val viewUrl: String? = null
)

data class StickerAttachment(val sticker: Sticker, override val type: String = "sticker") : Attachment

data class Sticker(
    val productId: Int,
    val stickerId: Int,
    val images: Array<ImagesArray>,
    val imagesWithBackground: Array<ImagesWithBackgroundArray>,
    val animationUrl: String,
    val isAllowed: Boolean
) {
    class ImagesArray(
        val url: String,
        val width: Int,
        val height: Int
    )

    class ImagesWithBackgroundArray(
        val url: String,
        val width: Int,
        val height: Int
    )
}

data class AudioAttachment(val audio: Audio, override val type: String = "audio") : Attachment

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: String,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean,
    val isHq: Boolean
)

data class GraffitiAttachment(val audio: Graffiti, override val type: String = "graffiti") : Attachment

data class Graffiti(
    val id: Int,
    val ownerId: Int,
    val url: String,
    val width: Int,
    val height: Int
)


