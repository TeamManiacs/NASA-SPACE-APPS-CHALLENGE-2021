package com.example.covid19_vaccinator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19_vaccinator.R;
import com.example.covid19_vaccinator.model.VaccineModel;

import java.util.List;

public class VaccineInfoAdapter extends RecyclerView.Adapter<VaccineInfoAdapter.ViewHolder> {

    private LayoutInflater LayoutInflater;
    private List<VaccineModel> list_vaccine_center;

    public VaccineInfoAdapter(Context mcontext, List<VaccineModel> list_vaccine_center) {
        this.LayoutInflater = LayoutInflater.from(mcontext);
        this.list_vaccine_center = list_vaccine_center;
    }

    @NonNull
    @Override
    public VaccineInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.inflate(R.layout.vaccination_info_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineInfoAdapter.ViewHolder holder, int position) {

     holder.vaccinationCenter.setText(list_vaccine_center.get(position).getVaccineCenter());

        holder.vaccinationCenterAddr.setText(list_vaccine_center.get(position).getVaccinationCenterAddress());
        holder.vaccinationAge.setText(list_vaccine_center.get(position).getVaccineAge());
        holder.vaccinationTimings.setText(list_vaccine_center.get(position).getVaccineTimings()+"-"+list_vaccine_center.get(position).getVaccineCenterTimings());
        holder.vaccineCharges.setText(list_vaccine_center.get(position).getVaccinationCharges());
        holder.vaccineName.setText(list_vaccine_center.get(position).getVaccineName());
        holder.vaccinationAvailable.setText(list_vaccine_center.get(position).getVaccineAvailable());

    }

    @Override
    public int getItemCount() {
        return list_vaccine_center.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView vaccinationCenter;
        TextView vaccinationCenterAddr;
        TextView vaccinationTimings;
        TextView vaccineName;
        TextView vaccineCharges;
        TextView vaccinationAge;
        TextView vaccinationAvailable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vaccinationAge=itemView.findViewById(R.id.vaccinationage);
            vaccinationAvailable=itemView.findViewById(R.id.isAvailable);
            vaccineCharges=itemView.findViewById(R.id.vaccineCharges);
            vaccineName=itemView.findViewById(R.id.vaccineName);
            vaccinationTimings=itemView.findViewById(R.id.vaccineTimings);
            vaccinationCenter=itemView.findViewById(R.id.vaccineCenter);
            vaccinationCenterAddr=itemView.findViewById(R.id.vaccineLocation);

        }
    }
}
