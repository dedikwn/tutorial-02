package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/hello2")
	public String hello2(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello2";
	}
	@RequestMapping(value={"/hello2","/hello2/{name}"})
	public String helloPath(@PathVariable Optional<String> name,Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		} else {
			model.addAttribute("name", "Phoenix");
		}
		return "hello2";
	}
	@RequestMapping("/calcuconvert")
	public String numberCalculate(@RequestParam(value = "angka1") String angka1, @RequestParam(value = "angka2") String angka2,
			Model model) {

		String[] angka0 = { "", " satu", " dua", " tiga", " empat", " lima", " enam", " tujuh", " delapan", " sembilan", " sepuluh" };
		String[] angka00 = { "", " belas", " dua puluh", "tiga puluh", " empat puluh", " lima puluh", " enam puluh", " tujuh puluh", " delapan puluh", " sembilan puluh" };
		String[] angka000 = { "", "seratus", "dua ratus", "tiga ratus", "empat ratus", "lima ratus", "enam ratus", "tujuh ratus", "delapan ratus", "sembilan ratus" };
		String[] angka0000 = { "", "seribu", "dua ribu", "tiga ribu", "empat ribu", "lima ribu", "enam ribu", "tujuh ribu", "delapan ribu", "sembilan ribu" };

		int number1 = Integer.parseInt(angka1);
		int number2 = Integer.parseInt(angka2);

		int total = number1 + number2;
		if (total < 9999 && total > 0) {
			int ribuan, ratusan, puluhan, satuan;
			ribuan = total / 1000;
			ratusan = (total % 1000) / 100;
			puluhan = (total % 100) / 10;
			satuan = total % 10;
			if (puluhan == 1) {
				if (satuan == 1) {
					model.addAttribute("terbilang", angka0000[ribuan] + angka000[ratusan] + " se" + angka00[puluhan]);
				} else {
					model.addAttribute("terbilang", angka0000[ribuan] + angka000[ratusan] + angka0[satuan] + angka00[puluhan]);
				}
			} else {
				model.addAttribute("terbilang", angka0000[ribuan] + angka000[ratusan] + angka00[puluhan] + angka0[satuan]);
			}
		} else {
			model.addAttribute("terbilang", "lebih dari 9999");
		}

		String total2 = Integer.toString(total);
		String hasil = angka1 + " + " + angka2;
		model.addAttribute("hasil", hasil);
		model.addAttribute("total2", total2);
		return "calcuconvert";
	}
}

