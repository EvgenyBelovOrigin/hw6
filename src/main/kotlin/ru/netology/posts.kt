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
    val attachments: Array<Attachment>? = null,
    val comments: Array<Comment>? = null
)


data class Likes(
    val count: Int,
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true


)

data class Comment(
    val id: Int,
    val text: String,
    val fromId: Int = 0,
    val date: Int = 1710415475,
    val donut: Donut? = null,
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachments: Array<Attachment>? = null,
    val parentsStack: Array<ParentsId>? = null,
    val thread: Thread? = null,
    var reportComment: Array<ReportComment>? = null


) {
    class Donut(
        val isDon: Boolean,
        val placeholder: String
    )

    class ParentsId(val parentsId: Int)
    class Thread(
        val count: Int,
//        val items:Array<Items>, TODO if necessary
        val canPost: Boolean,
        val showReplyButton: Boolean,
        val groupsCanPost: Boolean

    )
}

class PostNotFoundException(message: String) : RuntimeException(message)
class CommentNotFoundException(message: String) : RuntimeException(message)
class ReasonNotFoundException(message: String) : RuntimeException(message)
data class ReportComment(val ownerId: Int, val commentId: Int, val reason: Int)


object WallService {
    private var id = 11357

    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reportComments = emptyArray<ReportComment>()

    fun createReportComment(reportComment: ReportComment): ReportComment {
        for (comment in comments) {

            for (comment in comments) {
                when {
                    reportComment.reason < 0 || reportComment.reason > 8 ->
                        return throw ReasonNotFoundException(
                            "Reason № of report should be from 0 to 8 but current is ${reportComment.reason}"
                        )

                    reportComment.commentId == comment.id -> run {
                        reportComments += reportComment;
                        return reportComments.last()
                    }

                }
            }

        }
        return throw CommentNotFoundException("Comment with ID ${reportComment.commentId} doesn't exist")

    }


    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                return comments.last()
            }

        }
        return throw PostNotFoundException("Post ID $postId doesn't exist")

    }

    fun add(post: Post): Post {
        posts += post.copy(id = id, likes = post.likes?.copy(), comments = post.comments?.copyOf())
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

    fun printPosts() {
        for (post in posts) {
            println("ID: ${post.id} Post: ${post.text} Comment: ${post.comments?.get(0)?.text}")
        }
    }

    fun printComment() {
        for ((index) in comments.withIndex()) {
            println("$index text: ${comments.get(index).text}")
        }
    }

    fun printReportComment() {
        for ((index) in reportComments.withIndex()) {
            println("$index reason: ${reportComments.get(index).reason}")
        }
    }


}


fun main() {
    val post1 = Post("Hello")
    val post2 = Post("Bonjour")
    val post3 = Post("Ni Hao")
    val comment1 = Comment(1, "First comment")
    val comment2 = Comment(2, "Second comment")
    val comment3 = Comment(3, "Third comment")
    val reportComment1 = ReportComment(15, 1, 7)
    val reportComment2 = ReportComment(155, 1, 1)

    WallService.add((post1))
    WallService.add(post2)
    WallService.add(post3)
    WallService.createComment(11359, comment1)
    WallService.createComment(11357, comment2)
    WallService.createReportComment(reportComment1)
    WallService.createReportComment(reportComment2)

    WallService.printReportComment()

//    WallService.createComment(11358, comment1)

    WallService.printComment()


}