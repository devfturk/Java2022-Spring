package kodlama.io.rentAcar.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.rentAcar.business.abstracts.BrandService;
import kodlama.io.rentAcar.business.requests.CreateBrandRequest;
import kodlama.io.rentAcar.business.responses.GetAllBrandsResponse;

@RestController //annotation
@RequestMapping("/api/brands") //bu controller'a nasıl ulaşsın?
public class BrandsController {
	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	
	@GetMapping("/getall")
	public List<GetAllBrandsResponse> getAll() {
		return brandService.getAll();
	}
	@PostMapping("/add")
	public void add(@RequestBody() CreateBrandRequest createBrandRequest) { //RequestBody isteği bodysinden al demek default geliyor.
		this.brandService.add(createBrandRequest);
	}
}
