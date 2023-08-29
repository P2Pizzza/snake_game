package com.example.snake4


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import java.io.File
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt
import java.nio.file.Paths
import java.io.IOException

class MainActivity : Activity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val board = findViewById<RelativeLayout>(R.id.board)
        val border = findViewById<RelativeLayout>(R.id.relativeLayout)
        val managment = findViewById<LinearLayout>(R.id.managment)
        val upButton = findViewById<Button>(R.id.up)
        val downButton = findViewById<Button>(R.id.down)
        val leftButton = findViewById<Button>(R.id.left)
        val rightButton = findViewById<Button>(R.id.right)
        val newgame = findViewById<Button>(R.id.new_game)
        val playagain = findViewById<Button>(R.id.playagain)
        val score = findViewById<Button>(R.id.score)
        val score2 = findViewById<Button>(R.id.score2)
        val meat = ImageView(this)
        val snake = ImageView(this)
        val snakeSegments =
            mutableListOf(snake)
        val handler = Handler()
        var delayMillis = 40L
        var currentDirection = "up"
        var scorex = 0



        board.visibility = View.INVISIBLE
        playagain.visibility = View.INVISIBLE
        score.visibility = View.INVISIBLE
        score2.visibility = View.INVISIBLE

        newgame.setOnClickListener {
            board.visibility = View.VISIBLE
            newgame.visibility = View.INVISIBLE
            score2.visibility = View.VISIBLE


            snake.setImageResource(R.drawable.snake)
            snake.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            board.addView(snake)
            snakeSegments.add(snake)
            var snakeX = snake.x
            var snakeY = snake.y





            meat.setImageResource(R.drawable.meat)
            meat.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            board.addView(meat)

            val random = Random()
            val randomX =
                random.nextInt(801) - 400
            val randomY =
                random.nextInt(801) - 400


            meat.x = randomX.toFloat()
            meat.y = randomY.toFloat()


            fun checkFoodCollision() {
                val distanceThreshold = 50

                val distance = sqrt((snake.x - meat.x).pow(2) + (snake.y - meat.y).pow(2))

                if (distance < distanceThreshold) {

                    val newSnake =
                        ImageView(this)
                    newSnake.setImageResource(R.drawable.circle)
                    newSnake.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    snakeSegments.add(newSnake)
                    board.addView(newSnake)

                    val randomX = random.nextInt(801) + 100
                    val randomY = random.nextInt(801) + 100


                    meat.x = randomX.toFloat()
                    meat.y = randomY.toFloat()

                    scorex++

                    score2.text =   "score : " + scorex.toString()

                }
            }


            val runnable = object : Runnable {
                override fun run() {
                    for (i in snakeSegments.size - 1 downTo 1) {
                        snakeSegments[i].x = snakeSegments[i - 1].x
                        snakeSegments[i].y = snakeSegments[i - 1].y
                    }


                    when (currentDirection) {
                        "up" -> {
                            snakeY -= 10
                            var fl = true
                            for (j in snakeSegments.size - 1 downTo 3) {
                                val x = snakeSegments[j].x
                                val y = snakeSegments[j].y
                                if (x == snakeSegments[0].x && y == snakeSegments[0].y) {
                                    fl = false
                                }
                            }
                            if (snakeY < -490 || fl == false) {
                                border.setBackgroundColor(getResources().getColor(R.color.red))
                                playagain.visibility = View.VISIBLE
                                currentDirection = "pause"
                                managment.visibility = View.INVISIBLE

                                score.text =   "your score is  " + scorex.toString()
                                score.visibility = View.VISIBLE
                                score2.visibility = View.INVISIBLE
                                try {
                                    File("res.txt").writeText(scorex.toString())
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }
                            snake.translationY = snakeY
                        }
                        "down" -> {
                            snakeY += 10
                            val maxY =
                                board.height / 2 - snake.height + 30
                            var fl = true
                            for (j in snakeSegments.size - 1 downTo 3) {
                                val x = snakeSegments[j].x
                                val y = snakeSegments[j].y
                                if (x == snakeSegments[0].x && y == snakeSegments[0].y) {
                                    fl = false
                                }
                            }
                            if (snakeY > maxY || fl == false) {
                                border.setBackgroundColor(getResources().getColor(R.color.red))
                                playagain.visibility = View.VISIBLE
                                currentDirection = "pause"
                                managment.visibility = View.INVISIBLE

                                score.text =   "your score is  " + scorex.toString()
                                score.visibility = View.VISIBLE
                                score2.visibility = View.INVISIBLE
                                try {
                                    File("res.txt").writeText(scorex.toString())
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }
                            snake.translationY = snakeY
                        }
                        "left" -> {
                            snakeX -= 10
                            var fl = true
                            for (j in snakeSegments.size - 1 downTo 3) {
                                val x = snakeSegments[j].x
                                val y = snakeSegments[j].y
                                if (x == snakeSegments[0].x && y == snakeSegments[0].y) {
                                    fl = false
                                }
                            }
                            if (snakeX < -490 || fl == false) {
                                border.setBackgroundColor(getResources().getColor(R.color.red))
                                playagain.visibility = View.VISIBLE
                                currentDirection = "pause"
                                managment.visibility = View.INVISIBLE
                                score.text =   "your score is  " + scorex.toString()
                                score.visibility = View.VISIBLE
                                score2.visibility = View.INVISIBLE
                                try {
                                    File("res.txt").writeText(scorex.toString())
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }


                            }
                            snake.translationX = snakeX
                        }
                        "right" -> {
                            snakeX += 10
                            val maxX =
                                board.height / 2 - snake.height + 30
                            var fl = true
                            for (j in snakeSegments.size - 1 downTo 3) {
                                val x = snakeSegments[j].x
                                val y = snakeSegments[j].y
                                if (x == snakeSegments[0].x && y == snakeSegments[0].y) {
                                    fl = false
                                }
                            }
                            if (snakeX > maxX || fl == false) {
                                border.setBackgroundColor(getResources().getColor(R.color.red))
                                playagain.visibility = View.VISIBLE
                                currentDirection = "pause"
                                managment.visibility = View.INVISIBLE

                                score.text =   "your score is  " + scorex.toString()
                                score.visibility = View.VISIBLE
                                score2.visibility = View.INVISIBLE
                                try {
                                    File("res.txt").writeText(scorex.toString())
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }
                            snake.translationX = snakeX
                        }
                    }

                    checkFoodCollision()
                    handler.postDelayed(this, delayMillis)
                }
            }

            handler.postDelayed(runnable, delayMillis)

            upButton.setOnClickListener {
                currentDirection = "up"
            }
            downButton.setOnClickListener {
                currentDirection = "down"
            }
            leftButton.setOnClickListener {
                currentDirection = "left"
            }
            rightButton.setOnClickListener {
                currentDirection = "right"
            }
            playagain.setOnClickListener {
                recreate()
            }
        }
    }
}