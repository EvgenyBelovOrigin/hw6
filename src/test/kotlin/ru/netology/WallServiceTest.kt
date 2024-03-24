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
    fun wallService_commentAddedSuccess() {
        val post1 = Post("Hello")
        WallService.add(post1)
        val comment1 = Comment(1, "Comment added")
        val result = WallService.createComment(11357, comment1)
        assertEquals(result, comment1)


    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val post1 = Post("Hello")
        WallService.add(post1)
        val comment1 = Comment(1, "Comment added")
        WallService.createComment(11358, comment1)
    }


}