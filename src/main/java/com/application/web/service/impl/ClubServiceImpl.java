package com.application.web.service.impl;

import com.application.web.dto.ClubDto;
import com.application.web.repository.ClubRepository;
import com.application.web.service.ClubService;
import com.application.web.models.Club;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.application.web.mapper.ClubMapper.mapToClub;
import static com.application.web.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;

    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();

        return clubs.stream()
                .map((club) -> mapToClubDto(club))
                .collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long id) {
        Club club = clubRepository.findById(id).get();

        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void delete(long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream()
                .map((club) -> mapToClubDto(club))
                .collect(Collectors.toList());
    }
}
