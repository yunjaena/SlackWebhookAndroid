package dev.yunzai.slackwebhook

import com.google.gson.annotations.SerializedName

data class SlackWebHook(
    var attachments: ArrayList<Attachment>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()

        class Builder {
            private var pretext: String? = null
            private var fallback: String? = null
            private var title: String? = null
            private var text: String? = null
            private var authorName: String? = null
            private var authorLink: String? = null
            private var authorIcon: String? = null
            private var fields: ArrayList<Pair<String, String>>? = null
            private var color: String? = null
            private var isTimeStampEnable = false
            private var imageUrl: String? = null
            private var thumbUrl: String? = null
            private var footer: String? = null
            private var footerIcon: String? = null

            fun pretext(pretext: String) = apply {
                this.pretext = pretext
            }

            fun fallback(fallback: String) = apply {
                this.fallback = fallback
            }

            fun title(title: String) = apply {
                this.title = title
            }

            fun text(text: String) = apply {
                this.text = text
            }

            fun authorName(authorName: String) = apply {
                this.authorName = authorName
            }

            fun authorLink(authorLink: String) = apply {
                this.authorLink = authorLink
            }

            fun authorIcon(authorIcon: String) = apply {
                this.authorIcon = authorIcon
            }

            fun timeStampEnabled(isTimeStampEnable: Boolean)= apply {
                this.isTimeStampEnable = isTimeStampEnable
            }

            fun fields(vararg fields: Pair<String, String>) = apply {
                this.fields = arrayListOf(*fields)
            }

            fun color(color: String) = apply {
                this.color = color
            }

            fun imageUrl(imageUrl: String) = apply {
                this.imageUrl = imageUrl
            }

            fun thumbUrl(thumbUrl: String) = apply {
                this.thumbUrl = thumbUrl
            }

            fun footer(footer: String) = apply {
                this.footer = footer
            }

            fun footerIcon(footerIcon: String) = apply {
                this.footerIcon = footerIcon
            }

            fun build(): SlackWebHook {
                val attachment = Attachment()
                attachment.pretext = pretext
                attachment.fallback = fallback
                attachment.title = title
                attachment.text = text
                attachment.authorName = authorName
                attachment.authorLink = authorLink
                attachment.authorIcon = authorIcon
                attachment.color = color
                attachment.imageUrl = imageUrl
                attachment.thumbUrl = thumbUrl
                attachment.footer = footer
                attachment.footerIcon = footerIcon

                if (isTimeStampEnable)
                    attachment.timeStamp = (System.currentTimeMillis() / 1000).toString()

                if (!fields.isNullOrEmpty()) {
                    attachment.fields.addAll(fields!!.toFieldArrayList())
                }

                return SlackWebHook(
                    arrayListOf(attachment)
                )
            }
        }

        private fun ArrayList<Pair<String, String>>.toFieldArrayList(): ArrayList<Field> {
            val fieldArrayList = arrayListOf<Field>()
            this.forEach {
                fieldArrayList.add(Field(it.first, it.second, false))
            }
            return fieldArrayList
        }
    }


}

data class Attachment(
    var title: String? = null,
    var text: String? = null,
    var fallback: String? = null,
    var color: String? = null,
    var pretext: String? = null,
    @SerializedName("author_name") var authorName: String? = null,
    @SerializedName("author_link") var authorLink: String? = null,
    @SerializedName("author_icon") var authorIcon: String? = null,
    var fields: ArrayList<Field> = arrayListOf(),
    @SerializedName("ts") var timeStamp: String? = null,
    @SerializedName("image_url") var imageUrl: String? = null,
    @SerializedName("thumb_url") var thumbUrl: String? = null,
    var footer: String? = null,
    @SerializedName("footer_icon") var footerIcon: String? = null
)

data class Field(
    var title: String? = null,
    var value: String? = null,
    var short: Boolean = false
)
