package ru.netology

data class Post(
    val text: String,
    val id: Int = 0,
    val likes: Likes? = null,
    val ownerId: Int? = 0,
    val fromId: Int? = 0,
    val createdBy: Int? = 0,
    val date: Int = 1710410475,
    val friendOnly: Boolean = false,
    val canDelete: Boolean = false,
    val attachments: Array<Attachment>? = null
)


data class Likes(
    val count: Int,
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true


)


object WallService {
    private var id = 11357

    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        posts += post.copy(id = id, likes = post.likes?.copy())
        id++
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index) in posts.withIndex()) {
            if (posts[index].id == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }


    fun clear() {
        posts = emptyArray()
        id = 11357
    }


}

fun main() {
//    print("Hello")

}