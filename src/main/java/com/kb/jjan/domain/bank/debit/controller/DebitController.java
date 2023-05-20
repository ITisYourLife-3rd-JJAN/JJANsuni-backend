package com.kb.jjan.domain.bank.debit.controller;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.dto.DebitRequest;
import com.kb.jjan.domain.bank.debit.service.DebitService;
import com.kb.jjan.domain.bank.debit.dto.UserDebitResponse;
import com.kb.jjan.domain.user.dto.UserUpdatePriceRequest;
import com.kb.jjan.domain.user.service.UserService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kb.jjan.global.result.ResultCode.*;

@RequestMapping("api/v1/debits")
@RestController
@RequiredArgsConstructor
public class DebitController {

    private final DebitService debitService;
    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("")
    public ResponseEntity<ResultResponse> registerDebit(@RequestBody DebitRequest debitRequest)
            throws Exception {
        long sendUserId = debitService.registerDebit(debitRequest);
        Map<String, Long> item = new HashMap<>();
        item.put("sendUserId", sendUserId);

        ResultResponse<Long> resultResponse = new ResultResponse<>(DEBIT_REGISTRATION_SUCCESS, item);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<ResultResponse> showUserDebit(@PathVariable("userId") long userId)
            throws Exception{
        List<UserDebitResponse> debitResponses = debitService.showDebitHistory(userId);
        ResultResponse<List<Debit>> resultResponse = new ResultResponse<>(DEBIT_HISTORY_FINDBYIDUSER_SUCCESS, debitResponses);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse); // 있으면 list 값 담아서 보내줘야함

    }

    @PatchMapping("/charge")
    public ResponseEntity<ResultResponse> chargeBalance(@RequestBody UserUpdatePriceRequest userUpdatePriceRequest)
            throws Exception {
        int balance = userService.updateUserToDeposit(userUpdatePriceRequest);

        Map<String, Integer> item = new HashMap<>();
        item.put("balance", balance);

        ResultResponse<Integer> resultResponse = new ResultResponse<>( DEBIT_JJANPAY_CHARGE_SUCCESS, item);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

//    @GetMapping("/pay/{userId}")
//    public String generateQRCode(@PathVariable String userId) {
//        return "redirect:/pay/qr/" + userId;
//    }

    @GetMapping(value = "qr/{userId}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] generateQRCode(@PathVariable long userId) throws IOException, WriterException {
        String qrCodeText = "https://lifes.kbcard.com/CXLRIPWCD0029.cms?mainCC=a&sel="+(int) (Math.random() * 300+1);
        int width = 300;
        int height = 300;

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage qrCodeImage = debitService.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "png", baos);
        baos.flush();
        byte[] qrCodeBytes = baos.toByteArray();
        baos.close();

        return qrCodeBytes;
    }

}


