package com.parserdev.store.smartphone.presentation

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayout
import com.parserdev.store.domain.models.details.Image
import com.parserdev.store.domain.network.NetworkResult
import com.parserdev.store.smartphone.R
import com.parserdev.store.smartphone.databinding.FragmentSmartphoneBinding
import com.parserdev.store.smartphone.di.SmartphoneComponentProvider
import com.parserdev.store.utility.formatCurrency
import com.parserdev.store.utility.getColorFromAttr
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

class SmartphoneFragment : Fragment() {

    @Inject
    lateinit var smartphoneViewModelFactory: SmartphoneViewModelFactory
    private lateinit var smartphoneViewModel: SmartphoneViewModel

    private var _binding: FragmentSmartphoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSmartphoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectSmartphoneComponent()
        provideViewModel()
        binding.bindState()
    }

    private fun FragmentSmartphoneBinding.bindState() {
        bindTabLayout()
        bindNavigation()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                smartphoneViewModel.smartphoneDetails.collect { networkResult ->
                    when (networkResult) {
                        is NetworkResult.Success -> {
                            val data = networkResult.data
                            bindRating(
                                rating = data?.rating
                            )
                            bindCapacityButtons(
                                capacity1 = data?.capacity?.get(0) ?: "",
                                capacity2 = data?.capacity?.get(1) ?: ""
                            )
                            bindColorButtons(
                                color1 = data?.color?.get(0) ?: "",
                                color2 = data?.color?.get(1) ?: ""
                            )
                            bindViewPager(
                                images = data?.images?.map { Image(url = it) } ?: listOf()
                            )
                            bindSmartphoneInfo(
                                title = data?.title,
                                cpu = data?.cPU,
                                camera = data?.camera,
                                sd = data?.sd,
                                ssd = data?.ssd,
                                price = formatCurrency(
                                    price = data?.price,
                                    maximumFractionDigits = 2,
                                    currencyCode = "USD"
                                )
                            )
                            progressBar.visibility = View.GONE
                            layout.visibility = View.VISIBLE
                        }
                        is NetworkResult.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            layout.visibility = View.GONE
                        }
                        is NetworkResult.Error -> {}
                    }
                }
            }
        }
    }

    private fun FragmentSmartphoneBinding.bindNavigation() {
        buttonBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun FragmentSmartphoneBinding.bindCapacityButtons(
        capacity1: String,
        capacity2: String
    ) {
        fun selectCapacity(
            capacityCardView: CardView,
            capacityTextView: TextView
        ) {
            capacityCardView.isClickable = false
            capacityCardView.setCardBackgroundColor(
                requireContext().getColorFromAttr(
                    attrColor = android.R.attr.colorPrimary
                )
            )
            TextViewCompat.setTextAppearance(
                capacityTextView,
                R.style.Text_Details_Button_Selected
            )
            capacityTextView.text = capacityTextView.text.toString().uppercase()
        }

        fun deselectCapacity(
            capacityCardView: CardView,
            capacityTextView: TextView
        ) {
            capacityCardView.isClickable = true
            capacityCardView.setCardBackgroundColor(
                requireContext().getColorFromAttr(
                    attrColor = android.R.attr.textColor
                )
            )
            TextViewCompat.setTextAppearance(
                capacityTextView,
                R.style.Text_Details_Button_Deselected
            )
            capacityTextView.text = capacityTextView.text.toString().lowercase()
        }

        textMemory1.text = String.format(
            resources.getString(R.string.format_capacity, capacity1)
        ).uppercase()
        textMemory2.text = String.format(
            resources.getString(R.string.format_capacity, capacity2)
        )
        cardMemory1.setOnClickListener {
            smartphoneViewModel.accept(
                SmartphoneAction.UpdatePurchaseCapacity(
                    capacity = capacity1
                )
            )
            selectCapacity(
                capacityCardView = cardMemory1,
                capacityTextView = textMemory1
            )
            deselectCapacity(
                capacityCardView = cardMemory2,
                capacityTextView = textMemory2
            )
        }
        cardMemory2.setOnClickListener {
            smartphoneViewModel.accept(
                SmartphoneAction.UpdatePurchaseCapacity(
                    capacity = capacity2
                )
            )
            selectCapacity(
                capacityCardView = cardMemory2,
                capacityTextView = textMemory2
            )
            deselectCapacity(
                capacityCardView = cardMemory1,
                capacityTextView = textMemory1
            )
        }
    }

    private fun FragmentSmartphoneBinding.bindColorButtons(
        color1: String,
        color2: String
    ) {
        fun selectColor(
            card: CardView,
            imageView: ImageView
        ) {
            imageView.visibility = View.VISIBLE
            card.isClickable = false

        }

        fun deselectColor(
            card: CardView,
            imageView: ImageView
        ) {
            imageView.visibility = View.GONE
            card.isClickable = true
        }
        cardColor1.setCardBackgroundColor(
            Color.parseColor(
                color1
            )
        )
        cardColor2.setCardBackgroundColor(
            Color.parseColor(
                color2
            )
        )
        cardColor1.setOnClickListener {
            smartphoneViewModel.accept(
                SmartphoneAction.UpdatePurchaseColor(
                    color = color1
                )
            )
            selectColor(
                card = cardColor1,
                imageView = imageCheckMark1
            )
            deselectColor(
                card = cardColor2,
                imageView = imageCheckMark2
            )
        }
        cardColor2.setOnClickListener {
            smartphoneViewModel.accept(
                SmartphoneAction.UpdatePurchaseColor(
                    color = color2
                )
            )
            selectColor(
                card = cardColor2,
                imageView = imageCheckMark2
            )
            deselectColor(
                card = cardColor1,
                imageView = imageCheckMark1
            )
        }
    }

    private fun FragmentSmartphoneBinding.bindViewPager(
        images: List<Image>
    ) {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((30 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(compositePageTransformer)
            adapter =
                ImagesAdapter(images = images)
        }
    }

    private fun FragmentSmartphoneBinding.bindSmartphoneInfo(
        title: String?,
        cpu: String?,
        camera: String?,
        sd: String?,
        ssd: String?,
        price: String?
    ) {
        textTitle.text = title
        textCpu.text = cpu
        textCamera.text = camera
        textSd.text = sd
        textSsd.text = ssd
        textPrice.text = price
    }

    private fun FragmentSmartphoneBinding.bindTabLayout() {
        val firstTab = tabLayout.getTabAt(0)
        var views = arrayListOf<View>()
        firstTab?.view?.findViewsWithText(views, firstTab.text, View.FIND_VIEWS_WITH_TEXT)
        views.forEach { view ->
            if (view is TextView) {
                TextViewCompat.setTextAppearance(view, R.style.Text_Tab_Active)
            }
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {}

            override fun onTabUnselected(tab: TabLayout.Tab) {
                views = arrayListOf()
                tab.view.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
                views.forEach { view ->
                    if (view is TextView) {
                        TextViewCompat.setTextAppearance(view, R.style.Text_Tab_Inactive)
                    }
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                views = arrayListOf()
                tab.view.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
                views.forEach { view ->
                    if (view is TextView) {
                        TextViewCompat.setTextAppearance(view, R.style.Text_Tab_Active)
                    }
                }
            }
        })
    }

    private fun FragmentSmartphoneBinding.bindRating(rating: Double?) {
        when (rating) {
            0.5 -> groupRating.imageStar1Half.visibility = View.VISIBLE
            1.0 -> groupRating.imageStar1.visibility = View.VISIBLE
            1.5 -> {
                groupRating.imageStar1.visibility = View.VISIBLE
                groupRating.imageStar2Half.visibility = View.VISIBLE
            }
            2.0 -> {
                groupRating.imageStar1.visibility = View.VISIBLE
                groupRating.imageStar2.visibility = View.VISIBLE
            }
            2.5 -> {
                groupRating.imageStar1.visibility = View.VISIBLE
                groupRating.imageStar2.visibility = View.VISIBLE
                groupRating.imageStar3Half.visibility = View.VISIBLE
            }
            3.0 -> {
                groupRating.imageStar1.visibility = View.VISIBLE
                groupRating.imageStar2.visibility = View.VISIBLE
                groupRating.imageStar3.visibility = View.VISIBLE
            }
            3.5 -> {
                groupRating.imageStar1.visibility = View.VISIBLE
                groupRating.imageStar2.visibility = View.VISIBLE
                groupRating.imageStar3.visibility = View.VISIBLE
                groupRating.imageStar4Half.visibility = View.VISIBLE
            }
            4.0 -> {
                groupRating.imageStar1.visibility = View.VISIBLE
                groupRating.imageStar2.visibility = View.VISIBLE
                groupRating.imageStar3.visibility = View.VISIBLE
                groupRating.imageStar4.visibility = View.VISIBLE
            }
            4.5 -> {
                groupRating.imageStar1.visibility = View.VISIBLE
                groupRating.imageStar2.visibility = View.VISIBLE
                groupRating.imageStar3.visibility = View.VISIBLE
                groupRating.imageStar4.visibility = View.VISIBLE
                groupRating.imageStar5Half.visibility = View.VISIBLE
            }
            5.0 -> {
                groupRating.imageStar1.visibility = View.VISIBLE
                groupRating.imageStar2.visibility = View.VISIBLE
                groupRating.imageStar3.visibility = View.VISIBLE
                groupRating.imageStar4.visibility = View.VISIBLE
                groupRating.imageStar5.visibility = View.VISIBLE
            }
        }
    }

    private fun injectSmartphoneComponent() {
        val smartphoneComponent =
            (activity?.application as SmartphoneComponentProvider).provideSmartphoneComponent()
        smartphoneComponent.inject(this)
    }

    private fun provideViewModel() {
        smartphoneViewModel =
            ViewModelProvider(
                this,
                smartphoneViewModelFactory
            )[SmartphoneViewModel::class.java]
    }
}