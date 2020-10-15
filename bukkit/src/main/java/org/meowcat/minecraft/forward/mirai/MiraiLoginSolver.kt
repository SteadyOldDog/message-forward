package org.meowcat.minecraft.forward.mirai

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import net.mamoe.mirai.Bot
import net.mamoe.mirai.utils.*
import org.bukkit.Bukkit
import org.meowcat.minecraft.forward.Forward
import org.meowcat.minecraft.forward.mirai.BotLoginSolver.Companion.captchaChannel
import java.io.File

class MiraiLoginSolver : LoginSolver() {

    override suspend fun onSolvePicCaptcha(bot: Bot, data: ByteArray): String? {
        Bukkit.getConsoleSender()
        val logger = bot.logger
        val tempFile: File = createTempFile(suffix = ".png").apply { deleteOnExit() }
        withContext(Dispatchers.IO) {
            logger.info("需要图片验证码登录, 验证码为 4 字母")
            try {
                tempFile.createNewFile()
                tempFile.writeBytes(data)
                logger.info("请查看文件 ${tempFile.absolutePath}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        logger.info("请输入 4 位字母验证码. 若要更换验证码, 请直接回车")

        //需要验证码，开启通道并通知登录命令的发送者
        captchaChannel[bot.id]= Channel()
        return captchaChannel[bot.id]!!.receive()
    }

    override suspend fun onSolveSliderCaptcha(bot: Bot, url: String): String? {
        bot.logger.info("""
            需要滑动验证码
            请在任意浏览器中打开以下链接并完成验证码.
            完成后请输入任意字符
            $url
        """.trimIndent())
        //需要验证码，开启通道并通知登录命令的发送者
        captchaChannel[bot.id]= Channel()
        return captchaChannel[bot.id]!!.receive()
    }

    override suspend fun onSolveUnsafeDeviceLoginVerify(bot: Bot, url: String): String? {
        bot.logger.info("""
            "需要进行账户安全认证
            该账户有[设备锁]/[不常用登录地点]/[不常用设备登录]的问题
            完成以下账号认证即可成功登录|理论本认证在mirai每个账户中最多出现1次
            请将该链接在QQ浏览器中打开并完成认证, 成功后输入任意字符
            这步操作将在后续的版本中优化
            $url
        """.trimIndent())
        //需要帐号认证，开启通道并通知登录命令的发送者
        captchaChannel[bot.id]= Channel()
        return captchaChannel[bot.id]!!.receive()
    }
}