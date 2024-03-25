package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun wallServise_addCheckIdIncrease() {
        val post1 = Post("Hello and welcome")
        val result = WallService.add(post1).id
        assertEquals(result, 11357)
        val post2 = Post("Good Buy")
        val result2 = WallService.add(post2).id
        assertEquals(result2, 11358)
    }


    @Test
    fun wallServise_updateCheckTrue() {
        val post1 = Post("Hello and welcome")
        val post4 = Post("Yes!!", 11357)
        WallService.add(post1)
        val result = WallService.update(post4)
        assertTrue(result)

    }

    @Test
    fun wallServise_updateCheckFalse() {
        val post1 = Post("Hello and welcome")
        val post4 = Post("Yes!!", 11358)
        WallService.add(post1)
        val result = WallService.update(post4)
        assertFalse(result)

    }

    @Test
    fun wallService_commentAdditionSuccess() {
        val post1 = Post("Hello")
        WallService.add(post1)
        val comment1 = Comment(1, "Comment added")
        val result = WallService.createComment(11357, comment1)
        assertEquals(result, comment1)


    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrowPostNotFound() {
        val post1 = Post("Hello")
        WallService.add(post1)
        val comment1 = Comment(1, "Comment added")
        WallService.createComment(11358, comment1)
    }

    @Test
    fun wallService_reportCommentAdditionSuccess() {
        val post1 = Post("Hello")
        WallService.add(post1)
        val comment1 = Comment(1, "Comment added")
        WallService.createComment(11357, comment1)
        val reportComment1 = ReportComment(15, 1, 7)
        val result = WallService.createReportComment(reportComment1)
        assertEquals(result, reportComment1)


    }

    @Test(expected = CommentNotFoundException::class)
    fun shouldThrowCommentNotFound() {
        val post1 = Post("Hello")
        WallService.add(post1)
        val comment1 = Comment(1, "Comment added")
        WallService.createComment(11357, comment1)
        val reportComment1 = ReportComment(15, 2, 7)
        WallService.createReportComment(reportComment1)
    }

    @Test(expected = ReasonNotFoundException::class)
    fun shouldThrowReasonNotFound() {
        val post1 = Post("Hello")
        WallService.add(post1)
        val comment1 = Comment(1, "Comment added")
        WallService.createComment(11357, comment1)
        val reportComment1 = ReportComment(15, 1, 9)
        WallService.createReportComment(reportComment1)
    }


}