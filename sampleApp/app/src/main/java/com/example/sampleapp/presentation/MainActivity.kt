/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.sampleapp.presentation
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.media.MediaPlayer
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.activity.ComponentActivity
import androidx.core.view.InputDeviceCompat
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewConfigurationCompat
import com.example.sampleapp.R
import kotlin.concurrent.thread
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    private lateinit var mediaPlayer: MediaPlayer

    private val liste = listOf(R.layout.u0_view, R.layout.u1_view, R.layout.u2_view,R.layout.u3_view, R.layout.u4_view, R.layout.u5_view, R.layout.u6_view, R.layout.u7_view, R.layout.u8_view, R.layout.u9_view)
    private val ses_liste = listOf(R.raw.ses1, R.raw.ses2, R.raw.ses3, R.raw.ses3, R.raw.ses2, R.raw.ses1)
    private var currentIndexses = 0
    public var isAnimating = false
    private var currentIndex = 0
    private var currentses = 0
    private val longPressThreshold = 800L // 1 saniye
    public val context = this
    private var isClicked = false
    private var isClickedever = false

    private var isLongPressed = false
    private val handler = Handler(Looper.getMainLooper())

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_view)
        mediaPlayer = MediaPlayer.create(this, R.raw.ilk)

        val rootView = findViewById<View>(android.R.id.content)

        rootView.setOnTouchListener { _, event ->
            if (isAnimating){
                return@setOnTouchListener true
            }
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isLongPressed = false
                    handler.postDelayed({
                        if (!isClicked && !isLongPressed) {


                            val greenBoxView = findViewById<View>(R.id.greenBoxView)

                            val animator = ValueAnimator.ofFloat(0f, 1f)
                            animator.duration = 1000 // Animasyon süresi (örneğin, 2 saniye)

                            isAnimating = true

                            animator.addUpdateListener { animation ->
                                val alpha = animation.animatedValue as Float
                                greenBoxView.alpha = alpha
                            }
                            if (mediaPlayer.isPlaying) {
                                mediaPlayer.stop()

                            }

                            mediaPlayer.release() // Mevcut sesi serbest bırakın
                            mediaPlayer = MediaPlayer.create(this, R.raw.transform)
                            mediaPlayer.start()

                            animator.start()



                            // 1 saniye beklemek için bir Handler kullanımı
                            Handler(Looper.getMainLooper()).postDelayed({



                                animator.start()

                                // Bekleme süresi sona erdikten sonra bu blok çalışır
                                val animator2 = ValueAnimator.ofFloat(1f, 0f)
                                animator2.duration = 1500 // Animasyon süresi (örneğin, 2 saniye)
                                setContentView(R.layout.beyaz_view)
                                animator2.addUpdateListener { animation ->
                                    val greenBoxView = findViewById<View>(R.id.greenBoxView)

                                    val alpha = animation.animatedValue as Float
                                    greenBoxView.alpha = alpha
                                }
                                setContentView(R.layout.beyaz_view)
                                animator2.start()

                                Handler(Looper.getMainLooper()).postDelayed({

                                    if (mediaPlayer.isPlaying) {
                                        mediaPlayer.stop()

                                    }

                                    mediaPlayer.release() // Mevcut sesi serbest bırakın
                                    mediaPlayer = MediaPlayer.create(this, R.raw.puff)
                                    mediaPlayer.start()


                                    val greenBoxView = findViewById<View>(R.id.redView)

                                    val animator_red = ValueAnimator.ofFloat(0f, 0.5f)
                                    animator_red.duration = 400 // Animasyon süresi (örneğin, 2 saniye)

                                    animator_red.addUpdateListener { animation ->
                                        val alpha = animation.animatedValue as Float
                                        greenBoxView.alpha = alpha
                                    }



                                    val animator_red2 = ValueAnimator.ofFloat(0.5f, 0f,)
                                    animator_red2.duration = 400 // Animasyon süresi (örneğin, 2 saniye)

                                    animator_red2.addUpdateListener { animation ->
                                        val alpha = animation.animatedValue as Float
                                        greenBoxView.alpha = alpha
                                    }

                                    var carpan = 1L

                                    for (i in 0 until 3){
                                        handler.postDelayed({
                                            animator_red2.cancel()
                                            animator_red2.start()
                                        }, carpan * 300L)
                                        carpan ++
                                        handler.postDelayed({
                                            animator_red.cancel()
                                            animator_red.start()
                                        }, carpan * 300L)
                                        carpan ++

                                        handler.postDelayed({
                                            animator_red2.cancel()
                                            animator_red2.start()
                                        }, carpan * 300L)
                                        carpan ++
                                    }

                                    handler.postDelayed({
                                        val animator_exp = ValueAnimator.ofFloat(0f, 1f)
                                        animator_exp.duration = 400 // Animasyon süresi (örneğin, 2 saniye)

                                        animator_exp.addUpdateListener { animation ->
                                            val alpha = animation.animatedValue as Float
                                            greenBoxView.alpha = alpha
                                        }
                                        animator_exp.start()
                                    }, 3500L)


                                    handler.postDelayed({

                                        setContentView(R.layout.kirmizi_view)
                                        val greenBoxView = findViewById<View>(R.id.redView)
                                        val animator_end = ValueAnimator.ofFloat(1f, 0f)
                                        animator_end.duration = 800 // Animasyon süresi (örneğin, 2 saniye)

                                        animator_end.addUpdateListener { animation ->
                                            val alpha = animation.animatedValue as Float
                                            greenBoxView.alpha = alpha
                                        }
                                        animator_end.start()
                                    }, 5500L)

                                    handler.postDelayed({
                                        setContentView(R.layout.home_view)
                                        
                                        if (mediaPlayer.isPlaying) {
                                            mediaPlayer.stop()

                                        }

                                        mediaPlayer.release() // Mevcut sesi serbest bırakın
                                        mediaPlayer = MediaPlayer.create(this, R.raw.reset)
                                        mediaPlayer.start()


                                        isClicked = false
                                        isClickedever = false
                                        isLongPressed = false
                                        var currentIndexses = 0

                                        var currentIndex = 0
                                        var currentses = 0
                                        isAnimating = false
                                    }, 12000L)


                                }, 3000) // 3 saniye bekletme süresi

                            }, 2000) // Bekleme süresi 1000 milisaniye (1 saniye)

                        }
                    }, longPressThreshold)
                }
                MotionEvent.ACTION_UP -> {

                    handler.removeCallbacksAndMessages(null)
                    if (!isClicked && !isLongPressed) {

                        if (event.eventTime - event.downTime < longPressThreshold) {



                            if (!isClickedever){
                                setContentView(R.layout.u0_view)
                                currentIndex = 1
                                if (!mediaPlayer.isPlaying) {
                                    mediaPlayer = MediaPlayer.create(this, R.raw.ilk)
                                    mediaPlayer.start() // Ses dosyasını çal

                                    isClickedever = true
                                    return@setOnTouchListener true
                                }
                            }

                            val currentses = ses_liste[currentIndexses]
                            if (mediaPlayer.isPlaying) {
                                mediaPlayer.stop()

                            }
                            mediaPlayer.release() // Mevcut sesi serbest bırakın
                            mediaPlayer = MediaPlayer.create(this, currentses)
                            mediaPlayer.start()

                            currentIndexses = (currentIndexses + 1) % ses_liste.size // Listenin sonuna ulaşıldığında başa dön

                            val currentElement = liste[currentIndex]
                            setContentView(currentElement)
                            currentIndex = (currentIndex + 1) % liste.size // Listenin sonuna ulaşıldığında başa dön
                        }
                    }
                }
            }
            true
        }
    }
}


