/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Jmbosg
 */
public class DistanceBetweenPlacesDTO {

    private Long distance;

    private Long duration;

    public Long getDistance() {
        return distance;
    }

    public Long getDuration() {
        return duration;
    }

    public DistanceBetweenPlacesDTO(Long distance, Long duration) {
        this.distance = distance;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DistanceBetweenPlacesDTO other = (DistanceBetweenPlacesDTO) obj;
        if (!Objects.equals(this.distance, other.distance)) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        return true;
    }

}
