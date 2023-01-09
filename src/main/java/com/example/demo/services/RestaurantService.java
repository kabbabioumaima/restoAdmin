package com.example.demo.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.RestaurantDto;
import com.example.demo.model.Etat;
import com.example.demo.model.Photo;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Serie;
import com.example.demo.model.Specialite;
import com.example.demo.model.User;
import com.example.demo.model.Ville;
import com.example.demo.model.Zone;
import com.example.demo.repository.EtatRepository;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.SerieRepository;
import com.example.demo.repository.SpecialiteRepository;
import com.example.demo.repository.VilleRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.utils.EtatEnum;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private EtatRepository etatRepository;

	@Autowired
	private SerieRepository serieRepository;

	@Autowired
	private ZoneRepository zoneRepository;

	@Autowired
	private SpecialiteRepository specialiteRepository;

	@Autowired
	private PhotoRepository photoRepository;

	public List<Restaurant> getAll() {
		return restaurantRepository.findAll();
	}

	public List<Restaurant> getAllByUser(User user) {
		return restaurantRepository.findByOwner(user);
	}

	public List<Restaurant> getAllByZone(Zone zone) {
		return restaurantRepository.findByZone(zone);
	}

	public List<Restaurant> getAllBySerie(Long serie) {
		return restaurantRepository.findAllBySerie(serieRepository.getById(serie));
	}

	public List<Restaurant> getAllBySpecialite(Long spec) {
		System.out.println("spec");
		return restaurantRepository.findAllBySpeciality(specialiteRepository.getById(spec));
	}

	public List<Restaurant> getAllByVille(Ville ville) {
		return restaurantRepository.findByVille(ville.getId());
	}

	public Restaurant addRestaurant(MultipartFile[] files, String nom, String adresse, Long zone, Long serie,
			double lat, double longs, String ouvre, String ferme, Boolean week, Long speciality, User user) {

		Etat etat = etatRepository.findByName(EtatEnum.PENDING.name());
		Restaurant r = new Restaurant();

		r.setNom(nom);
		r.setAdresse(adresse);
		r.setHeure_cl(ferme);
		r.setHeure_op(ouvre);
		r.setLat(lat);
		r.setLongs(longs);
		r.setWeek(week);
		r.setOwner(user);
		r.setEtat(etat);
		r.setRank(0);
		r.setSerie(serieRepository.getById(serie));
		r.setZone(zoneRepository.getById(zone));
		r.setSpeciality(specialiteRepository.getById(speciality));
		restaurantRepository.save(r);
		r = addPhotosToRestau(r, files);
		return restaurantRepository.save(r);
	}

	public Restaurant addImages(MultipartFile[] files, Long id) {
		Restaurant restaurant = restaurantRepository.getById(id);
		addPhotosToRestau(restaurant, files);
		return restaurantRepository.save(restaurant);
	}

	public long count() {
		return restaurantRepository.count();
	}
	
	public void delete(Long id) {
		restaurantRepository.deleteById(id);
	}

	// ------------------------------------------------------------- NO API

	private Restaurant addPhotosToRestau(Restaurant restaurant, MultipartFile[] photos) {

		String fileName = new String();
		Set<Photo> pics = new HashSet<Photo>();

		for (MultipartFile pic : photos) {
			fileName = StringUtils.cleanPath(pic.getOriginalFilename());
			if (fileName.contains("..")) {
				System.out.println("not a valid file");
			}
			try {
				Photo photo = new Photo();
				photo.setImage(resizeImageForUse(Base64.getEncoder().encodeToString(pic.getBytes()), 400, 400));
				photo.setRestaurant(restaurant);
				photoRepository.save(photo);
				pics.add(photo);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		restaurant.setPhotos(pics);
		return restaurant;
	}

	public String resizeImageForUse(String src, int width, int height) {
		BufferedImage image = base64ToBufferedImage(src);
		try {
			image = resizeImage(image, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return bufferedImageTobase64(image);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BufferedImage resizeImage(BufferedImage image, int width, int height) throws IOException {
		ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
		try {
			Thumbnails.of(image).size(width, height).outputFormat("JPEG").outputQuality(1).toOutputStream(outputstream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] data = outputstream.toByteArray();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		return ImageIO.read(inputStream);
	}

	private BufferedImage base64ToBufferedImage(String base64Img) {
		BufferedImage image = null;
		byte[] decodedBytes = Base64.getDecoder().decode(base64Img);

		try {
			image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	private String bufferedImageTobase64(BufferedImage image) throws UnsupportedEncodingException {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "JPEG", Base64.getEncoder().wrap(out));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out.toString(StandardCharsets.ISO_8859_1.name());
	}
}
