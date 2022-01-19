package ir.arinateam.shopadmin.shop

import android.app.Activity
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import ir.arinateam.imagepicker.ImagePicker
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.databinding.AddBookFragmentBinding

class AddBookFragment : Fragment() {

    private lateinit var bindingFragment: AddBookFragmentBinding

    private lateinit var imgBack: ImageView
    private lateinit var tvPageTitle: TextView
    private lateinit var btnAddEdit: Button
    private lateinit var imgBook: ShapeableImageView
    private lateinit var addImage: ImageView
    private lateinit var edBookName: EditText
    private lateinit var edBookWriter: EditText
    private lateinit var edBookPublisher: EditText
    private lateinit var spBookCategory: Spinner
    private lateinit var edBookPrice: EditText
    private lateinit var edBookPageCount: EditText
    private lateinit var edBookPublishYear: EditText
    private lateinit var edBookISBN: EditText
    private lateinit var edBookAvailableCount: EditText
    private lateinit var edBookDiscount: EditText
    private lateinit var edBookDescription: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.add_book_fragment, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        checkBundle()

        getCategoryList()

        addOrEditProduct()

        getImage()

        backToProducts()

    }

    private fun initView() {

        imgBack = bindingFragment.imgBack
        tvPageTitle = bindingFragment.tvPageTitle
        btnAddEdit = bindingFragment.btnAddEdit
        imgBook = bindingFragment.imgBook
        addImage = bindingFragment.addImage
        edBookName = bindingFragment.edBookName
        edBookWriter = bindingFragment.edBookWriter
        edBookPublisher = bindingFragment.edBookPublisher
        spBookCategory = bindingFragment.spBookCategory
        edBookPrice = bindingFragment.edBookPrice
        edBookPageCount = bindingFragment.edBookPageCount
        edBookPublishYear = bindingFragment.edBookPublishYear
        edBookISBN = bindingFragment.edBookISBN
        edBookAvailableCount = bindingFragment.edBookAvailableCount
        edBookDiscount = bindingFragment.edBookDiscount
        edBookDescription = bindingFragment.edBookDescription

    }

    private fun checkBundle() {

        if (arguments != null) {

            requireArguments().getInt("", 0)
            tvPageTitle.text = "ویرایش کتاب"
            btnAddEdit.text = "ویرایش"


            bookId = requireArguments().getInt("productId")
            bookImage = requireArguments().getString("productImage")!!
            bookName = requireArguments().getString("productName")!!
            bookWriter = requireArguments().getString("productWriter")!!
            bookPublisher = requireArguments().getString("productPublisher")!!
            bookCategory = requireArguments().getString("productCategoryId")!!
            bookPrice = requireArguments().getString("productPrice")!!
            bookPageCount = requireArguments().getString("productPageCount")!!
            bookPublishYear = requireArguments().getString("productPublishYear")!!
            bookISBN = requireArguments().getString("productIsbn")!!
            bookAvailableCount = requireArguments().getString("productAvailableCount")!!
            bookDiscount = requireArguments().getString("productDiscountPercent")!!
            bookDescription = requireArguments().getString("productDescription")!!

            Glide.with(requireActivity()).load(bookImage).diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter().placeholder(
                    R.drawable.ic_admin_image_test
                ).into(imgBook)

            edBookName.setText(bookName)
            edBookWriter.setText(bookWriter)
            edBookPublisher.setText(bookPublisher)
            edBookPrice.setText(bookPrice)
            edBookPageCount.setText(bookPageCount)
            edBookPublishYear.setText(bookPublishYear)
            edBookISBN.setText(bookISBN)
            edBookAvailableCount.setText(bookAvailableCount)
            edBookDiscount.setText(bookDiscount)
            edBookDescription.setText(bookDescription)

        }

    }

    private fun getCategoryList() {


    }

    private fun addOrEditProduct() {

        btnAddEdit.setOnClickListener {

            if (arguments != null) {

                bookId = requireArguments().getInt("productId", 0)

                checkInputs()

            } else {

                checkInputs()

            }

        }

    }

    private var bookId: Int? = null
    private lateinit var bookImage: String
    private lateinit var bookName: String
    private lateinit var bookWriter: String
    private lateinit var bookPublisher: String
    private lateinit var bookCategory: String
    private lateinit var bookPrice: String
    private lateinit var bookPageCount: String
    private lateinit var bookPublishYear: String
    private lateinit var bookISBN: String
    private lateinit var bookAvailableCount: String
    private lateinit var bookDiscount: String
    private lateinit var bookDescription: String

    private fun checkInputs() {

        bookName = edBookName.text.toString().plus("")
        bookWriter = edBookWriter.text.toString().plus("")
        bookPublisher = edBookPublisher.text.toString().plus("")
        bookCategory = edBookName.text.toString().plus("") //TODO("گرفتن ای دی دسته بندی")
        bookPrice = edBookPrice.text.toString().plus("")
        bookPageCount = edBookPageCount.text.toString().plus("")
        bookPublishYear = edBookPublishYear.text.toString().plus("")
        bookISBN = edBookISBN.text.toString().plus("")
        bookAvailableCount = edBookAvailableCount.text.toString().plus("")
        bookDiscount = edBookDiscount.text.toString().plus("")
        bookDescription = edBookDescription.text.toString().plus("")


        if (bookName != "" && bookWriter != "" && bookPublisher != "" && bookCategory != "" && bookPrice != "" && bookPageCount != "" && bookPublishYear != "" && bookISBN != "" && bookAvailableCount != "" && bookDescription != "") {

            edBookName.setText("")
            edBookWriter.setText("")
            edBookPublisher.setText("")
            edBookPrice.setText("")
            edBookPageCount.setText("")
            edBookPublishYear.setText("")
            edBookISBN.setText("")
            edBookAvailableCount.setText("")
            edBookDiscount.setText("")
            edBookDescription.setText("")

            Toast.makeText(requireActivity(), "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show()

        } else {

            Toast.makeText(
                requireActivity(),
                "لطفا تمامی ورودی ها را وارد نمایید!",
                Toast.LENGTH_SHORT
            ).show()

        }


    }

    private fun getImage() {

        imgBook.setOnClickListener {

            ImagePicker.with(this)
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    700,
                    800
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }

        }

    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                val bitmap = MediaStore.Images.Media.getBitmap(
                    requireActivity().contentResolver,
                    fileUri
                )

                imgBook.setImageURI(fileUri)

            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    private fun backToProducts() {

        imgBack.setOnClickListener {

            Navigation.findNavController(it).popBackStack()

        }

    }

}