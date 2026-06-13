package com.recovery.recoverytool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.recovery.recoverytool.service.UrgeRecordService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UrgeRecordService urgeRecordService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/red")
    public String red() {
        return "red";
    }

    @GetMapping("/intensity")
    public String intensity() {
        return "intensity";
    }

    @GetMapping("/emotion")
    public String emotion() {
        return "emotion";
    }

    @GetMapping("/trigger")
    public String trigger() {
        return "trigger";
    }

    @GetMapping("/need")
    public String need() {
        return "need";
    }

    @GetMapping("/past")
    public String past(Model model) {

        model.addAttribute(
                "record",
                urgeRecordService.getRandomRecord());

        return "past";
    }

    @GetMapping("/action")
    public String action() {
        return "action";
    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @GetMapping("/summary")
    public String summary() {
        return "summary";
    }

    @GetMapping("/finish")
    public String finish() {
        return "finish";
    }

    @GetMapping("/history")
    public String history(Model model) {

        model.addAttribute(
                "records",
                urgeRecordService.getAllRecords());

        return "history";
    }

    @GetMapping("/history/{id}")
    public String historyDetail(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "record",
                urgeRecordService.getRecordById(id));

        return "history-detail";
    }

    @GetMapping("/stats")
    public String stats(Model model) {

        model.addAttribute(
                "triggerStats",
                urgeRecordService.getTriggerStats());

        model.addAttribute(
                "needStats",
                urgeRecordService.getNeedStats());

        return "stats";
    }

    @PostMapping("/saveIntensity")
    public String saveIntensity(
            @RequestParam("intensity") Integer intensity,
            HttpSession session) {

        session.setAttribute(
                "intensity",
                intensity);

        return "redirect:/emotion";
    }

    @PostMapping("/saveEmotion")
    public String saveEmotion(
            @RequestParam("emotion") String emotion,
            HttpSession session) {

        session.setAttribute(
                "emotion",
                emotion);

        return "redirect:/trigger";
    }

    @PostMapping("/saveTrigger")
    public String saveTrigger(
            @RequestParam("triggerReason") String triggerReason,
            HttpSession session) {

        session.setAttribute(
                "triggerReason",
                triggerReason);

        return "redirect:/need";
    }

    @PostMapping("/saveNeed")
    public String saveNeed(
            @RequestParam("needReason") String needReason,
            HttpSession session) {

        session.setAttribute(
                "needReason",
                needReason);

        return "redirect:/past";
    }

    @PostMapping("/saveAction")
    public String saveAction(
            @RequestParam("actionTaken") String actionTaken,
            HttpSession session) {

        session.setAttribute(
                "actionTaken",
                actionTaken);

        return "redirect:/result";
    }

    @PostMapping("/saveResult")
    public String saveResult(
            @RequestParam("resultText") String resultText,
            HttpSession session) {

        session.setAttribute(
                "resultText",
                resultText);

        return "redirect:/summary";
    }

    @PostMapping("/saveSummary")
    public String saveSummary(
            @RequestParam("summaryText") String summaryText,
            HttpSession session) {

        urgeRecordService.saveSummary(
                summaryText,
                session);

        return "redirect:/finish";
    }
}